package com.exasol.cloudetl.kinesis

import java.nio.file.Paths
import java.sql.ResultSet

import com.exasol.containers.ExasolContainer
import com.exasol.dbbuilder.dialects.Column
import com.exasol.dbbuilder.dialects.exasol._
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript

import com.amazonaws.SDKGlobalConfiguration.AWS_CBOR_DISABLE_SYSTEM_PROPERTY
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.kinesis.AmazonKinesis
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

trait KinesisAbstractIntegrationTest extends AnyFunSuite with BeforeAndAfterAll {
  val JAR_FILE_NAME = IntegrationTestConstants.JAR_FILE_NAME
  val DEFAULT_EXASOL_DOCKER_IMAGE = IntegrationTestConstants.DEFAULT_EXASOL_DOCKER_IMAGE
  val DOCKER_IP_ADDRESS = "172.17.0.1"
  val TEST_SCHEMA_NAME = "kinesis_schema"
  val LOCALSTACK_DOCKER_IMAGE = IntegrationTestConstants.LOCALSTACK_DOCKER_IMAGE

  val exasolContainer = {
    val c: ExasolContainer[_] = new ExasolContainer(getExasolDockerImageVersion())
    c.withReuse(true)
    c
  }
  val kinesisLocalStack: LocalStackContainer =
    new LocalStackContainer(DockerImageName.parse(LOCALSTACK_DOCKER_IMAGE))
      .withServices(LocalStackContainer.Service.KINESIS)
      .withReuse(false)

  var schema: ExasolSchema = _
  var factory: ExasolObjectFactory = _

  private[this] var connection: java.sql.Connection = _
  var statement: java.sql.Statement = _
  private[kinesis] var kinesisClient: AmazonKinesis = _

  private[kinesis] def prepareContainers(): Unit = {
    exasolContainer.start()
    connection = exasolContainer.createConnectionForUser(
      exasolContainer.getUsername,
      exasolContainer.getPassword
    )
    statement = connection.createStatement()
    kinesisLocalStack.start()
    val endpoint = kinesisLocalStack.getEndpointOverride(LocalStackContainer.Service.KINESIS).toString()
    val credentials = new BasicAWSCredentials(kinesisLocalStack.getAccessKey(), kinesisLocalStack.getSecretKey())
    kinesisClient = AmazonKinesisClientBuilder.standard
      .withEndpointConfiguration(new EndpointConfiguration(endpoint, kinesisLocalStack.getRegion()))
      .withCredentials(new AWSStaticCredentialsProvider(credentials))
      .build
    System.setProperty(AWS_CBOR_DISABLE_SYSTEM_PROPERTY, "true")
    ()
  }

  private[kinesis] def setupExasol(): Unit = {
    uploadJarToBucket(JAR_FILE_NAME)
    val exasolConfiguration = ExasolObjectConfiguration
      .builder()
      .withJvmOptions("-Dcom.amazonaws.sdk.disableCbor=true")
      .build()
    factory = new ExasolObjectFactory(connection, exasolConfiguration)
    schema = factory.createSchema(TEST_SCHEMA_NAME)
    createConnectionObject()
    ()
  }

  private[this] def createConnectionObject(): Unit = {
    val accessKey = kinesisLocalStack.getAccessKey()
    val secretKey = kinesisLocalStack.getSecretKey()
    val secret = s"AWS_ACCESS_KEY=$accessKey;AWS_SECRET_KEY=$secretKey"
    factory.createConnectionDefinition("KINESIS_CONNECTION", "", "user", secret)
    ()
  }

  private[this] def uploadJarToBucket(assembledJarName: String): Unit = {
    val pathToJar = Paths.get("target", assembledJarName)
    exasolContainer.getDefaultBucket.uploadFile(pathToJar, assembledJarName)
  }

  private[kinesis] def createKinesisStream(streamName: String, shardsCounter: Integer): Unit = {
    kinesisClient.createStream(streamName, shardsCounter)
    // We have to wait until stream is ready to be accessed.
    Thread.sleep(30 * 1000)
  }

  private[kinesis] def createKinesisMetadataScript(): Unit = {
    schema
      .createUdfBuilder("KINESIS_METADATA")
      .language(UdfScript.Language.JAVA)
      .inputType(UdfScript.InputType.SET)
      .emits(
        new Column("KINESIS_SHARD_ID", "VARCHAR(130)"),
        new Column("SHARD_SEQUENCE_NUMBER", "VARCHAR(2000)")
      )
      .bucketFsContent(
        "com.exasol.cloudetl.kinesis.KinesisShardsMetadataReader",
        s"/buckets/bfsdefault/default/$JAR_FILE_NAME"
      )
      .build()
    ()
  }

  private[kinesis] def createKinesisImportScript(emittedColumns: Seq[Column]): Unit = {
    statement.execute(s"DROP SCRIPT IF EXISTS KINESIS_IMPORT")
    val udfScript = schema
      .createUdfBuilder("KINESIS_IMPORT")
      .language(UdfScript.Language.JAVA)
      .inputType(UdfScript.InputType.SET)
      .bucketFsContent(
        "com.exasol.cloudetl.kinesis.KinesisShardDataImporter",
        s"/buckets/bfsdefault/default/$JAR_FILE_NAME"
      )
    if (emittedColumns.isEmpty) {
      udfScript.emits().build()
    } else {
      udfScript.emits(emittedColumns: _*).build()
    }
    ()
  }

  private[kinesis] def collectResultSet[T](resultSet: ResultSet)(func: ResultSet => T): List[T] =
    new Iterator[T] {
      def hasNext = resultSet.next()
      def next() = func(resultSet)
    }.toList

  private[this] def getExasolDockerImageVersion(): String = {
    val dockerVersion = System.getenv("EXASOL_DOCKER_VERSION")
    if (dockerVersion == null) {
      DEFAULT_EXASOL_DOCKER_IMAGE
    } else {
      dockerVersion
    }
  }

  override final def afterAll(): Unit = {
    connection.close()
    statement.close()
    kinesisClient.shutdown()
    exasolContainer.stop()
    kinesisLocalStack.stop()
  }
}
