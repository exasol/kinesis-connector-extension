# Kinesis Connector Extension 1.1.1, released 2023-10-26

Code name: Fix CVE-2023-42503

## Summary

This release fixes vulnerability CVE-2023-42503 in transitive test dependency to `org.apache.commons:commons-compress` via `exasol-testcontainers` by updating dependencies.

## Security

* #38: Fixed vulnerability CVE-2023-42503 in test dependency `org.apache.commons:commons-compress`

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Compile Dependency Updates

* Updated `com.amazonaws:aws-java-sdk-kinesis:1.12.540` to `1.12.573`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.1` to `6.6.2`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.0` to `0.5.4`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.0` to `1.6.1`
* Updated `com.exasol:test-db-builder-java:3.5.0` to `3.5.1`
* Updated `org.mockito:mockito-core:5.5.0` to `5.6.0`
* Updated `org.testcontainers:localstack:1.19.0` to `1.19.1`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.3.0` to `1.3.1`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.11` to `2.9.14`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.0` to `3.4.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.0` to `2.16.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.10` to `0.8.11`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `3.10.0.2594`
