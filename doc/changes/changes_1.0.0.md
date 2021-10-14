# Kinesis Connector Extension 1.0.0, released 2021-10-15

Code name: Refactoring and dependency updates

## Summary

In this release, we refactoring integration tests to use `test-db-builder-java` library. We also migrated to Github Actions from Travis CI, and updated dependency versions.

## Refactoring

* #21: Refactored integration tests using test-db-builder-java
* #23: Migrated to Github Actions from Travis CI
* #25: Updated Jackson dependencies

## Dependency updates

### Compile Dependency Updates

* Updated `com.exasol:import-export-udf-common-scala:0.2.0` to `0.3.1`
* Updated `com.amazonaws:aws-java-sdk-core:1.11.916` to `1.12.88`
* Updated `com.amazonaws:aws-java-sdk-kinesis:1.11.916` to `1.12.88`
* Removed `com.fasterxml.jackson.core:jackson-databind:2.12.0`
* Removed `com.fasterxml.jackson.module:jackson-module-scala:2.12.0`
* Removed `com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:2.12.0`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:3.4.0` to `5.1.0`
* Updated `com.exasol:test-db-builder-java:3.0.0` to `3.2.1`
* Updated `org.testcontainers:localstack:1.15.0` to `1.16.0`
* Updated `org.mockito:mockito-core:3.5.10` to `4.0.0`
* Updated `org.scalatest:scalatest:3.2.2` to `3.2.10`

### Plugin Updates

* Added `org.scalameta:sbt-scalafmt:2.4.3`
* Updated `com.eed3si9n:sbt-assembly:0.15.0` to `1.1.0`
* Updated `com.timushev.sbt:sbt-updates:0.5.1` to `0.6.0`
* Updated `org.scoverage:sbt-coveralls:1.2.7` to `1.3.1`
* Updated `org.scoverage:sbt-scoverage:1.6.1` to `1.9.1`
* Updated `org.wartremover:sbt-wartremover:2.4.13` to `2.4.16`
* Updated `org.wartremover:sbt-wartremover-contib:1.3.11` to `1.3.12`
* Removed `com.typesafe.sbt:sbt-git:1.0.0`
* Removed `com.lucidchart:sbt-scalafmt-coursier:1.16`
* Removed `com.github.cb372:sbt-explicit-dependencies:0.2.15`
