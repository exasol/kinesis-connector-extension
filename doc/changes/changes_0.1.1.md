# Kinesis Connector Extension 0.1.1, released 2020-12-14

Code name: Bug fix for Exasol 7 and jackson-core/jackson-dataformat-cbor dependency problem

## Bug Fixes

* #18: Fixed jackson-core/jackson-dataformat-cbor incompatible library version.

## Refactoring

* #11: Fixed Travis continuous integration build badge.
* #13: Fixed the Travis Github Deployment Issue.

## Documentation

* #15: Added a link to the development guide.
* #17: Added a link to the tech blog article about Kinesis Connector.

## Dependency updates

* Added `com.fasterxml.jackson.core:jackson-dataformat-cbor:2.12.0`
* Updated `com.fasterxml.jackson.core:jackson-databind:2.11.2` to `2.12.0`
* Updated `com.fasterxml.jackson.core:jackson-module-scala:2.11.2` to `2.12.0`
* Updated `com.exasol:exasol-testcontainers:3.0.0` to `3.4.0`
* Updated `org.testcontainers:localstack:1.14.3` to `1.15.0`
* Updated `com.amazonaws:aws-java-sdk-core:1.11.860` to `1.11.916`
* Updated `com.exasol:import-export-udf-common-scala:0.1.0` to `0.2.0`
