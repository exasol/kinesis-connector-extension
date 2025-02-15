# Kinesis Connector Extension 1.1.8, released 2025-02-15

Code name: Fix CWE-346, CVE-2024-4068, CVE-2025-24970 and CVE-2025-25193

## Summary

This release updates the 3rd-party JavaScript libraries `esbuild` and `braces`to fix the vulnerabilities CWE-346 and CVE-2024-4068

## Features

* #61: Fix CVE-2024-4068 in braces
* #62: Fix CVE-2025-25193 in netty
* #63: Fix CVE-2025-24970 in netty

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.13` to `0.5.15`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.4.0` to `4.5.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.1` to `3.5.2`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.9.1` to `3.21.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.1` to `3.5.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.17.1` to `2.18.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121` to `5.0.0.4389`
