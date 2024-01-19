package com.exasol.cloudetl.kinesis

import java.nio.file.Paths
import java.sql.ResultSet

import com.exasol.containers.ExasolContainer
import com.exasol.dbbuilder.dialects.Column
import com.exasol.dbbuilder.dialects.exasol._
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

trait KinesisAbstractIntegrationTest extends AnyFunSuite with BeforeAndAfterAll {
  val JAR_FILE_NAME = IntegrationTestConstants.JAR_FILE_NAME
  val DEFAULT_EXASOL_DOCKER_IMAGE = IntegrationTestConstants.DEFAULT_EXASOL_DOCKER_IMAGE
  val DOCKER_IP_ADDRESS = IntegrationTestConstants.DOCKER_IP_ADDRESS
  val TEST_SCHEMA_NAME = "kinesis_schema"
  val LOCALSTACK_DOCKER_IMAGE = IntegrationTestConstants.LOCALSTACK_DOCKER_IMAGE

  val exasolContainer = {
    val c: ExasolContainer[_] = new ExasolContainer(getExasolDockerImageVersion())
    c.withReuse(true)
    c
  }
  val kinesisSetup: KinesisTestSetup = KinesisTestSetup.create()

  var schema: ExasolSchema = _
  var factory: ExasolObjectFactory = _

  private[this] var connection: java.sql.Connection = _
  var statement: java.sql.Statement = _

  private[kinesis] def prepareContainers(): Unit = {
    exasolContainer.start()
    connection = exasolContainer.createConnectionForUser(
      exasolContainer.getUsername,
      exasolContainer.getPassword
    )
    statement = connection.createStatement()
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
    val accessKey = kinesisSetup.getAccessKey()
    val secretKey = kinesisSetup.getSecretKey()
    val secret = s"AWS_ACCESS_KEY=$accessKey;AWS_SECRET_KEY=$secretKey"
    factory.createConnectionDefinition("KINESIS_CONNECTION", "", "user", secret)
    ()
  }

  private[this] def uploadJarToBucket(assembledJarName: String): Unit = {
    val pathToJar = Paths.get("target", assembledJarName)
    exasolContainer.getDefaultBucket.uploadFile(pathToJar, assembledJarName)
  }

  private[kinesis] def createKinesisStream(streamName: String, shardsCounter: Integer): KinesisTestSetup.KinesisStream =
    kinesisSetup.createStream(streamName, shardsCounter)

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
    val dockerVersion = System.getenv("EXASOL_DB_VERSION")
    if (dockerVersion == null) {
      DEFAULT_EXASOL_DOCKER_IMAGE
    } else {
      dockerVersion
    }
  }

  override final def afterAll(): Unit = {
    connection.close()
    statement.close()
    exasolContainer.stop()
    kinesisSetup.close()
  }
}
