package com.exasol.cloudetl.kinesis

import com.amazonaws.SDKGlobalConfiguration.AWS_CBOR_DISABLE_SYSTEM_PROPERTY
import com.amazonaws.services.kinesis.{AmazonKinesis, AmazonKinesisClientBuilder}
import com.exasol.containers.ExasolContainer
import com.exasol.dbbuilder.dialects.Column
import com.exasol.dbbuilder.dialects.exasol._
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName

import java.io.File
import java.nio.file.Paths
import java.sql.ResultSet

trait KinesisAbstractIntegrationTest extends AnyFunSuite with BeforeAndAfterAll {
  val JAR_DIRECTORY_PATTERN = "scala-"
  val JAR_NAME_PATTERN = "exasol-kinesis-connector-extension-"
  val DOCKER_IP_ADDRESS = "172.17.0.1"
  val TEST_SCHEMA_NAME = "kinesis_schema"

  val exasolContainer = new ExasolContainer("7.0.5")
  val kinesisLocalStack: LocalStackContainer =
    new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.12.5"))
      .withServices(LocalStackContainer.Service.KINESIS)

  var assembledJarName: String = _
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
    kinesisClient = AmazonKinesisClientBuilder.standard
      .withEndpointConfiguration(
        kinesisLocalStack.getEndpointConfiguration(LocalStackContainer.Service.KINESIS)
      )
      .withCredentials(kinesisLocalStack.getDefaultCredentialsProvider)
      .build
    System.setProperty(AWS_CBOR_DISABLE_SYSTEM_PROPERTY, "true")
    ()
  }

  private[kinesis] def setupExasol(): Unit = {
    assembledJarName = findAssembledJarName()
    uploadJarToBucket(assembledJarName)
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
    val credentials = kinesisLocalStack.getDefaultCredentialsProvider.getCredentials
    val awsAccessKey = credentials.getAWSAccessKeyId()
    val awsSecretKey = credentials.getAWSSecretKey()
    val secret = s"AWS_ACCESS_KEY=$awsAccessKey;AWS_SECRET_KEY=$awsSecretKey"
    factory.createConnectionDefinition("KINESIS_CONNECTION", "", "user", secret)
    ()
  }

  private[this] def uploadJarToBucket(assembledJarName: String): Unit = {
    val pathToJar = Paths.get("target", getScalaDirectory(), assembledJarName)
    exasolContainer.getDefaultBucket.uploadFile(pathToJar, assembledJarName)
  }

  private[this] def getScalaDirectory(): String =
    findFileOrDirectory("target", JAR_DIRECTORY_PATTERN)

  private[this] def findFileOrDirectory(directoryToSearch: String, name: String): String = {
    val files = listDirectoryFiles(directoryToSearch)
    val jarFile = files.find(_.getName.contains(name))
    jarFile match {
      case Some(jarFilename) => jarFilename.getName
      case None =>
        throw new IllegalArgumentException(
          "Cannot find a file or a directory with pattern" + name + " in " + directoryToSearch
        )
    }
  }

  private[kinesis] def createKinesisStream(streamName: String, shardsCounter: Integer): Unit = {
    kinesisClient.createStream(streamName, shardsCounter)
    // We have to wait until stream is ready to be accessed.
    Thread.sleep(30 * 1000)
  }

  def findAssembledJarName(): String = {
    val scalaDirectory = getScalaDirectory()
    findFileOrDirectory("target/" + scalaDirectory, JAR_NAME_PATTERN)
  }

  private[this] def listDirectoryFiles(directoryName: String): List[File] = {
    val directory = new File(directoryName)
    if (directory.exists && directory.isDirectory) {
      directory.listFiles.toList
    } else {
      List.empty[File]
    }
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
        s"/buckets/bfsdefault/default/$assembledJarName"
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
        s"/buckets/bfsdefault/default/$assembledJarName"
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

  override final def afterAll(): Unit = {
    connection.close()
    statement.close()
    kinesisClient.shutdown()
    exasolContainer.stop()
    kinesisLocalStack.stop()
  }
}
