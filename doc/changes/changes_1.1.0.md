# Exasol Kinesis Connector Extension 1.1.0, released 2023-08-25

Code name: Add Extension

## Summary

This release adds support for the [Extension Manager](https://github.com/exasol/extension-manager/).

## Feature

* #31: Added support for Extension Manager

## Refactoring

* #30: Migrated from sbt to Maven

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Compile Dependency Updates

* Added `com.amazonaws:aws-java-sdk-kinesis:1.12.537`
* Added `com.exasol:error-reporting-java:1.0.1`
* Added `com.exasol:import-export-udf-common-scala_2.13:1.1.1`
* Added `org.scala-lang:scala-library:2.13.3`

#### Test Dependency Updates

* Added `com.exasol:exasol-testcontainers:6.6.1`
* Added `com.exasol:extension-manager-integration-test-java:0.5.0`
* Added `com.exasol:hamcrest-resultset-matcher:1.6.0`
* Added `com.exasol:test-db-builder-java:3.4.2`
* Added `org.mockito:mockito-core:5.5.0`
* Added `org.scalatestplus:scalatestplus-mockito_2.13:1.0.0-SNAP5`
* Added `org.scalatest:scalatest_2.13:3.3.0-SNAP4`
* Added `org.testcontainers:localstack:1.19.0`

#### Plugin Dependency Updates

* Added `com.diffplug.spotless:spotless-maven-plugin:2.38.0`
* Added `com.exasol:artifact-reference-checker-maven-plugin:0.4.2`
* Added `com.exasol:error-code-crawler-maven-plugin:1.3.0`
* Added `com.exasol:project-keeper-maven-plugin:2.9.10`
* Added `io.github.evis:scalafix-maven-plugin_2.13:0.1.8_0.11.0`
* Added `io.github.zlika:reproducible-build-maven-plugin:0.16`
* Added `net.alchim31.maven:scala-maven-plugin:4.8.1`
* Added `org.apache.maven.plugins:maven-assembly-plugin:3.6.0`
* Added `org.apache.maven.plugins:maven-clean-plugin:2.5`
* Added `org.apache.maven.plugins:maven-compiler-plugin:3.11.0`
* Added `org.apache.maven.plugins:maven-deploy-plugin:2.7`
* Added `org.apache.maven.plugins:maven-enforcer-plugin:3.3.0`
* Added `org.apache.maven.plugins:maven-failsafe-plugin:3.1.2`
* Added `org.apache.maven.plugins:maven-install-plugin:2.4`
* Added `org.apache.maven.plugins:maven-jar-plugin:3.3.0`
* Added `org.apache.maven.plugins:maven-javadoc-plugin:3.5.0`
* Added `org.apache.maven.plugins:maven-resources-plugin:2.6`
* Added `org.apache.maven.plugins:maven-site-plugin:3.3`
* Added `org.apache.maven.plugins:maven-surefire-plugin:3.1.2`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:2.0.1`
* Added `org.codehaus.mojo:flatten-maven-plugin:1.5.0`
* Added `org.codehaus.mojo:versions-maven-plugin:2.16.0`
* Added `org.jacoco:jacoco-maven-plugin:0.8.10`
* Added `org.scalastyle:scalastyle-maven-plugin:1.0.0`
* Added `org.scalatest:scalatest-maven-plugin:2.2.0`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
* Added `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.2.0`

### Extension

#### Compile Dependency Updates

* Added `@exasol/extension-manager-interface:0.3.1`

#### Development Dependency Updates

* Added `eslint:^8.47.0`
* Added `@jest/globals:^29.6.3`
* Added `@types/node:^20.5.4`
* Added `@typescript-eslint/parser:^6.4.1`
* Added `ts-jest:^29.1.1`
* Added `typescript:^5.1.6`
* Added `@typescript-eslint/eslint-plugin:^6.4.1`
* Added `jest:29.6.3`
* Added `ts-node:^10.9.1`
* Added `esbuild:^0.19.2`
