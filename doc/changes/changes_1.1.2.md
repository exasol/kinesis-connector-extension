# Kinesis Connector Extension 1.1.2, released 2023-11-21

Code name: Fix CVE-2023-4043 in test dependency `org.eclipse.parsson:parsson`

## Summary

This release fixes vulnerability CVE-2023-4043 in test dependency `org.eclipse.parsson:parsson`. It also extends the matrix build to not only verify Exasol database version 8 but also the latest 7.1.x.

## Security

* #44: Fixed CVE-2023-4043 in test dependency `org.eclipse.parsson:parsson`

## Refactoring

* #42: Extended matrix build to verify version 7.1.x of Exasol DB again

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Compile Dependency Updates

* Updated `com.amazonaws:aws-java-sdk-kinesis:1.12.573` to `1.12.593`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.2` to `6.6.3`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.4` to `0.5.7`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.1` to `1.6.3`
* Updated `com.exasol:test-db-builder-java:3.5.1` to `3.5.2`
* Updated `org.mockito:mockito-core:5.6.0` to `5.7.0`
* Updated `org.testcontainers:localstack:1.19.1` to `1.19.2`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.14` to `2.9.16`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.1.2` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.1.2` to `3.2.2`
