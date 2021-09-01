# Kinesis Connector Extension 1.0.0, released 2021-??-??

Code name:

## Summary

## Refactoring

* #21: Refactored integration tests using test-db-builder-java
* #23: Migrated to Github Actions from Travis CI

## Dependency updates

### Compile Dependency Updates

* Updated `com.amazonaws:aws-java-sdk-core:1.11.916` to `1.12.58`
* Updated `com.amazonaws:aws-java-sdk-kinesis:1.11.916` to `1.12.58`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:3.4.0` to `4.0.1`
* Updated `com.exasol:test-db-builder-java:3.0.0` to `3.2.1`
* Updated `org.testcontainers:localstack:1.15.0` to `1.16.0`
* Updated `org.mockito:mockito-core:3.5.10` to `3.12.4`
* Updated `org.scalatest:scalatest:3.2.2` to `3.2.9`

### Plugin Updates

* Added `org.scalameta:sbt-scalafmt:2.4.3`
* Updated `com.eed3si9n:sbt-assembly:0.15.0` to `1.0.0`
* Updated `com.timushev.sbt:sbt-updates:0.5.1` to `0.6.0`
* Updated `org.scoverage:sbt-coveralls:1.2.7` to `1.3.1`
* Updated `org.scoverage:sbt-scoverage:1.6.1` to `1.8.2`
* Updated `org.wartremover:sbt-wartremover:2.4.13` to `2.4.16`
* Updated `org.wartremover:sbt-wartremover-contib:1.3.11` to `1.3.12`
* Removed `com.typesafe.sbt:sbt-git:1.0.0`
* Removed `com.lucidchart:sbt-scalafmt-coursier:1.16`
* Removed `com.github.cb372:sbt-explicit-dependencies:0.2.15`
