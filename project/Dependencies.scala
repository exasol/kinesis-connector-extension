package com.exasol.cloudetl.sbt

import sbt.{ExclusionRule, _}
import sbt.librarymanagement.InclExclRule

/** A list of required dependencies */
object Dependencies {

  // Runtime dependencies versions
  private val ExasolVersion = "6.1.7"
  private val ImportExportUDFVersion = "0.1.0"
  private val AwsJavaSdkVersion = "1.11.860"

  // Test dependencies versions
  private val ScalaTestVersion = "3.2.2"
  private val ScalaTestPlusVersion = "1.0.0-M2"
  private val MockitoCoreVersion = "3.5.10"
  private val ExasolTestContainersVersion = "3.0.0"
  private val TestContainersLocalstackVersion = "1.14.3"
  private val FasterxmlJacksonVersion = "2.11.2"

  val Resolvers: Seq[Resolver] = Seq(
    "Confluent Maven Repo" at "https://packages.confluent.io/maven/",
    "Exasol Releases" at "https://maven.exasol.com/artifactory/exasol-releases"
  )

  lazy val RuntimeDependencies: Seq[ModuleID] = Seq(
    "com.exasol" % "exasol-script-api" % ExasolVersion,
    "com.exasol" %% "import-export-udf-common-scala" % ImportExportUDFVersion,
    "com.amazonaws" % "aws-java-sdk-core" % AwsJavaSdkVersion,
    "com.amazonaws" % "aws-java-sdk-kinesis" % AwsJavaSdkVersion,
    "com.fasterxml.jackson.core" % "jackson-databind" % FasterxmlJacksonVersion,
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % FasterxmlJacksonVersion
  )

  lazy val TestDependencies: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % ScalaTestVersion,
    "org.scalatestplus" %% "scalatestplus-mockito" % ScalaTestPlusVersion,
    "org.mockito" % "mockito-core" % MockitoCoreVersion,
    "com.exasol" % "exasol-testcontainers" % ExasolTestContainersVersion,
    "org.testcontainers" % "localstack" % TestContainersLocalstackVersion
  ).map(_ % Test)

  lazy val AllDependencies: Seq[ModuleID] = RuntimeDependencies ++ TestDependencies

  lazy val ExcludedDependencies: Seq[InclExclRule] = Seq(
    ExclusionRule("org.openjfx", "javafx.base")
  )

}
