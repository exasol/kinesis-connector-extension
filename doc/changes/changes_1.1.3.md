# Kinesis Connector Extension 1.1.3, released 2024-01-19

Code name: Fix CVE-2024-21634 in `software.amazon.ion:ion-java`

## Summary

This release fixes vulnerability CVE-2024-21634 in transitive compile dependency `software.amazon.ion:ion-java`.

## Security

* #48: Fixed CVE-2024-21634 in `software.amazon.ion:ion-java`

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.16` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.2` to `3.2.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.2` to `3.2.3`
* Added `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.1` to `2.16.2`

### Extension

#### Compile Dependency Updates

* Updated `@exasol/extension-manager-interface:0.3.1` to `0.4.1`

#### Development Dependency Updates

* Updated `eslint:^8.47.0` to `^8.56.0`
* Updated `@jest/globals:^29.6.3` to `^29.7.0`
* Updated `@types/node:^20.5.4` to `^20.11.5`
* Updated `@typescript-eslint/parser:^6.4.1` to `^6.19.0`
* Updated `typescript:^5.1.6` to `^5.3.3`
* Updated `@typescript-eslint/eslint-plugin:^6.4.1` to `^6.19.0`
* Updated `jest:29.6.3` to `29.7.0`
* Updated `ts-node:^10.9.1` to `^10.9.2`
* Updated `esbuild:^0.19.2` to `^0.19.11`
