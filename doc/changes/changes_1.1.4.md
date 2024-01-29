# Kinesis Connector Extension 1.1.4, released 2024-01-30

Code name: Scala upgraded to fix CVE-2022-36944

## Summary
Scala upgraded to fix CVE-2022-36944. Project Keeper plugin updated. 
Utility build script fixed to work in Mac OS. Couple of other dependencies updated.

## Features

* #36: Upgrade to Scala 2.13.11 to fix CVE-2022-36944

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Compile Dependency Updates

* Updated `com.amazonaws:aws-java-sdk-kinesis:1.12.639` to `1.12.646`
* Updated `org.scala-lang:scala-library:2.13.3` to `2.13.12`

#### Test Dependency Updates

* Updated `org.mockito:mockito-core:5.9.0` to `5.10.0`
* Updated `org.testcontainers:localstack:1.19.3` to `1.19.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:3.0.0` to `3.0.1`
