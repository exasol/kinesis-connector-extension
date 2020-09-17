# Kinesis Connector Extension 0.1.0, released 2020-??-??

## Summary

In this release we removed deprecated properties AWS_ACCESS_KEY, AWS_SECRET_KEY and AWS_SESSION_TOKEN.
Please, provide credentials using a named connection. Check the user guide for more details.

## Feature

* #1: Added initial implementation.

## Refactoring

* #5: Removed deprecated properties AWS_ACCESS_KEY, AWS_SECRET_KEY and AWS_SESSION_TOKEN.
* #6: Renamed a script KINESIS_PATH to KINESIS_CONSUMER in tests and documentation.

## Documentation

* #2: Updated documentation.

## Dependency updates

<details>
  <summary>Click to expand</summary>

* Added `com.amazonaws:aws-java-sdk-core:1.11.861`
* Added `com.amazonaws:aws-java-sdk-kinesis:1.11.861`
* Added `com.exasol:exasol-script-api:6.1.7`
* Added `com.exasol:import-export-udf-common-scala:0.1.0`
* Added `com.exasol:import-export-udf-common-scala:0.1.0`
* Added `com.exasol:exasol-testcontainers:test:3.0.0`
* Added `org.testcontainers:localstack:test:1.14.3`
 
 </details> 