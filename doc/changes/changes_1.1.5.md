# Kinesis Connector Extension 1.1.5, released 2024-03-18

Code name: Fix CVE-2024-25710 and CVE-2024-26308 in test dependency

## Summary

This release fixes CVE-2024-25710 and CVE-2024-26308 in test dependency `org.apache.commons:commons-compress:1.24.0`.

## Security

* #50: Fixed CVE-2024-25710 in `org.apache.commons:commons-compress:jar:1.24.0:test`
* #51: Fixed CVE-2024-26308 in `org.apache.commons:commons-compress:jar:1.24.0:test`

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Compile Dependency Updates

* Updated `com.amazonaws:aws-java-sdk-kinesis:1.12.646` to `1.12.680`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.0.0` to `7.0.1`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.7` to `0.5.8`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.4` to `1.6.5`
* Updated `com.exasol:test-db-builder-java:3.5.3` to `3.5.4`
* Updated `org.mockito:mockito-core:5.10.0` to `5.11.0`
* Updated `org.testcontainers:localstack:1.19.4` to `1.19.7`

#### Plugin Dependency Updates

* Updated `com.diffplug.spotless:spotless-maven-plugin:2.39.0` to `2.43.0`
* Updated `com.exasol:error-code-crawler-maven-plugin:1.3.1` to `2.0.0`
* Updated `com.exasol:project-keeper-maven-plugin:3.0.1` to `4.1.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.11.0` to `3.12.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.3` to `3.2.5`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.5.0` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.3` to `3.2.5`
* Updated `org.codehaus.mojo:exec-maven-plugin:3.1.0` to `3.2.0`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.5.0` to `1.6.0`

### Extension

#### Development Dependency Updates

* Updated `eslint:^8.56.0` to `^8.57.0`
* Updated `@types/node:^20.11.5` to `^20.11.28`
* Updated `@typescript-eslint/parser:^6.19.0` to `^7.2.0`
* Updated `ts-jest:^29.1.1` to `^29.1.2`
* Updated `typescript:^5.3.3` to `^5.4.2`
* Updated `@typescript-eslint/eslint-plugin:^6.19.0` to `^7.2.0`
* Updated `esbuild:^0.19.11` to `^0.20.2`
