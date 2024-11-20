# Kinesis Connector Extension 1.1.7, released 2024-11-20

Code name: Fix CVE-2024-47535 & CVE-2024-47561

## Summary

This release fixes vulnerabilities
* CVE-2024-47535 in `io.netty:netty-common:jar:4.1.108.Final:test`
* CVE-2024-47561 in `org.apache.avro:avro:jar:1.11.3:compile`

## Security

* #59: Fixed CVE-2024-47535 in `io.netty:netty-common:jar:4.1.108.Final:test`
* #58: Fixed CVE-2024-47561 in `org.apache.avro:avro:jar:1.11.3:compile`

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Compile Dependency Updates

* Updated `com.amazonaws:aws-java-sdk-kinesis:1.12.698` to `1.12.778`
* Added `com.exasol:import-export-udf-common-scala:2.0.1`
* Removed `com.exasol:import-export-udf-common-scala_2.13:2.0.0`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.0.1` to `7.1.1`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.9` to `0.5.13`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Added `com.exasol:maven-project-version-getter:1.2.1`
* Updated `com.exasol:test-db-builder-java:3.5.4` to `3.6.0`
* Updated `org.mockito:mockito-core:5.11.0` to `5.14.2`
* Updated `org.testcontainers:localstack:1.19.7` to `1.20.3`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.2` to `2.0.3`
* Updated `com.exasol:project-keeper-maven-plugin:4.3.0` to `4.4.0`
* Added `com.exasol:quality-summarizer-maven-plugin:0.2.0`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.16` to `0.17`
* Updated `org.apache.maven.plugins:maven-clean-plugin:2.5` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.5` to `3.5.1`
* Updated `org.apache.maven.plugins:maven-install-plugin:2.4` to `3.1.3`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.3.0` to `3.4.2`
* Updated `org.apache.maven.plugins:maven-resources-plugin:2.6` to `3.3.1`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.3` to `3.9.1`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.5` to `3.5.1`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0` to `3.2.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.2` to `2.17.1`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922` to `4.0.0.4121`

### Extension

#### Compile Dependency Updates

* Updated `@exasol/extension-manager-interface:0.4.1` to `0.4.3`

#### Development Dependency Updates

* Updated `eslint:^8.57.0` to `9.14.0`
* Updated `@types/node:^20.11.28` to `^22.9.1`
* Updated `ts-jest:^29.1.2` to `^29.2.5`
* Added `typescript-eslint:^8.14.0`
* Updated `typescript:^5.4.2` to `^5.6.3`
* Updated `esbuild:^0.20.2` to `^0.24.0`
* Removed `@typescript-eslint/parser:^7.2.0`
* Removed `@typescript-eslint/eslint-plugin:^7.2.0`
