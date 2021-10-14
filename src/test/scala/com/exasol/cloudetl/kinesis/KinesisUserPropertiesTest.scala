package com.exasol.cloudetl.kinesis

import com.exasol.{ExaConnectionInformation, ExaMetadata}
import com.exasol.cloudetl.kinesis.KinesisUserProperties._

import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KinesisUserPropertiesTest extends AnyFunSuite with MockitoSugar {
  private[this] val propertiesMap = Map(
    "TABLE_NAME" -> "TEST_TABLE",
    "CONNECTION_NAME" -> "MY_CONNECTION",
    "AWS_ACCESS_KEY" -> "MY_ACCESS_KEY",
    "AWS_SECRET_KEY" -> "MY_SECRET_KEY",
    "AWS_SESSION_TOKEN" -> "MY_SESSION_TOKEN",
    "STREAM_NAME" -> "Test_stream",
    "REGION" -> "eu-west-1",
    "AWS_SERVICE_ENDPOINT" -> "http://127.0.0.1:40215",
    "MAX_RECORDS_PER_RUN" -> "1000"
  )
  test("getAwsAccessKeyProperty returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getAwsAccessKey() === "MY_ACCESS_KEY")
  }

  test("containsAwsSecretKey returns true") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.containsAwsSecretKey() === true)
  }

  test("getAwsSecretKeyProperty returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getAwsSecretKey() === "MY_SECRET_KEY")
  }

  test("getAwsSessionTokenProperty returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getAwsSessionToken() === "MY_SESSION_TOKEN")
  }

  test("containsAwsSessionToken returns true") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.containsAwsSessionToken() === true)
  }

  test("getAwsServiceEndpoint returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getAwsServiceEndpoint() === "http://127.0.0.1:40215")
  }

  test("containsAwsServiceEndpoint returns true") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.containsAwsServiceEndpoint() === true)
  }

  test("getStreamNameProperty returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getStreamName() === "Test_stream")
  }

  test("getRegionProperty returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getRegion() === "eu-west-1")
  }

  test("getTableNameProperty returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getTableName() === "TEST_TABLE")
  }

  test("containsMaxRecordsPerRun returns true") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.containsMaxRecordsPerRun() === true)
  }

  test("getMaxRecordsPerRun returns value from Map") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(kinesisUserProperties.getMaxRecordsPerRun() === 1000)
  }

  test("getAwsAccessKeyProperty throws an exception as the property is missing") {
    val kinesisUserProperties = new KinesisUserProperties(Map.empty[String, String])
    val thrown = intercept[IllegalArgumentException] {
      kinesisUserProperties.getAwsAccessKey()
    }
    assertMissingPropertyKey(thrown.getMessage(), AWS_ACCESS_KEY_PROPERTY)
  }

  test("getAwsSecretKeyProperty throws an exception as the property is missing") {
    val kinesisUserProperties = new KinesisUserProperties(Map.empty[String, String])
    val thrown = intercept[IllegalArgumentException] {
      kinesisUserProperties.getAwsSecretKey()
    }
    assertMissingPropertyKey(thrown.getMessage(), AWS_SECRET_KEY_PROPERTY)
  }

  test("getAwsSessionTokenProperty throws an exception as the property is missing") {
    val kinesisUserProperties = new KinesisUserProperties(Map.empty[String, String])
    val thrown = intercept[IllegalArgumentException] {
      kinesisUserProperties.getAwsSessionToken()
    }
    assertMissingPropertyKey(thrown.getMessage(), AWS_SESSION_TOKEN_PROPERTY)
  }

  test("getStreamNameProperty throws an exception as the property is missing") {
    val kinesisUserProperties = new KinesisUserProperties(Map.empty[String, String])
    val thrown = intercept[IllegalArgumentException] {
      kinesisUserProperties.getStreamName()
    }
    assertMissingPropertyKey(thrown.getMessage(), STREAM_NAME_PROPERTY)
  }

  test("getRegionProperty throws an exception as the property is missing") {
    val kinesisUserProperties = new KinesisUserProperties(Map.empty[String, String])
    val thrown = intercept[IllegalArgumentException] {
      kinesisUserProperties.getRegion()
    }
    assertMissingPropertyKey(thrown.getMessage(), REGION_PROPERTY)
  }

  test("getTableNameProperty throws an exception as the property is missing") {
    val kinesisUserProperties = new KinesisUserProperties(Map.empty[String, String])
    val thrown = intercept[IllegalArgumentException] {
      kinesisUserProperties.getTableName()
    }
    assertMissingPropertyKey(thrown.getMessage(), TABLE_NAME_PROPERTY)
  }

  private[this] def assertMissingPropertyKey(message: String, propertyKey: String): Unit = {
    assert(message.contains(s"Please provide key-value pairs for '$propertyKey' property."))
    ()
  }

  test("mergeWithConnectionObject returns new KinesisUserProperties") {
    val propertiesMap = Map(
      "TABLE_NAME" -> "TEST_TABLE",
      "CONNECTION_NAME" -> "MY_CONNECTION",
      "STREAM_NAME" -> "Test_stream",
      "REGION" -> "eu-west-1"
    )
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    val exaMetadata = mock[ExaMetadata]
    val exaConnectionInformation = mock[ExaConnectionInformation]
    when(exaMetadata.getConnection("MY_CONNECTION")).thenReturn(exaConnectionInformation)
    when(exaConnectionInformation.getUser()).thenReturn("")
    when(exaConnectionInformation.getPassword())
      .thenReturn(
        """AWS_ACCESS_KEY=MY_ACCESS_KEY;
          |AWS_SECRET_KEY=MY_SECRET_KEY;
          |AWS_SESSION_TOKEN=MY_SESSION_TOKEN""".stripMargin.replace("\n", "")
      )
    val mergedKinesisUserProperties = kinesisUserProperties.mergeWithConnectionObject(exaMetadata)
    assert(
      mergedKinesisUserProperties.mkString() ===
        """AWS_ACCESS_KEY -> MY_ACCESS_KEY;
          |AWS_SECRET_KEY -> MY_SECRET_KEY;
          |AWS_SESSION_TOKEN -> MY_SESSION_TOKEN;
          |CONNECTION_NAME -> MY_CONNECTION;
          |REGION -> eu-west-1;
          |STREAM_NAME -> Test_stream;
          |TABLE_NAME -> TEST_TABLE""".stripMargin
          .replace("\n", "")
    )
  }

  test("mergeWithConnectionObject throws exception during validation") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    val exaMetadata = mock[ExaMetadata]
    val thrown = intercept[KinesisConnectorException] {
      kinesisUserProperties.mergeWithConnectionObject(exaMetadata)
    }
    assert(thrown.getMessage.contains("E-KIN-PROP-1"))
  }

  test("mkString returns a properties string") {
    val kinesisUserProperties = new KinesisUserProperties(propertiesMap)
    assert(
      kinesisUserProperties.mkString() ===
        """AWS_ACCESS_KEY -> MY_ACCESS_KEY;
          |AWS_SECRET_KEY -> MY_SECRET_KEY;
          |AWS_SERVICE_ENDPOINT -> http://127.0.0.1:40215;
          |AWS_SESSION_TOKEN -> MY_SESSION_TOKEN;
          |CONNECTION_NAME -> MY_CONNECTION;
          |MAX_RECORDS_PER_RUN -> 1000;
          |REGION -> eu-west-1;
          |STREAM_NAME -> Test_stream;
          |TABLE_NAME -> TEST_TABLE""".stripMargin.replace("\n", "")
    )
  }
}
