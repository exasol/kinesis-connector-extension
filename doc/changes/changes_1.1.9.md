# Kinesis Connector Extension 1.1.9, released 2025-??-??

Code name: Fixed vulnerability CVE-2024-55551 in com.exasol:exasol-jdbc:jar:24.1.1:test

## Summary

This release fixes the following vulnerability:

### CVE-2024-55551 (CWE-94) in dependency `com.exasol:exasol-jdbc:jar:24.1.1:test`
An issue was discovered in Exasol jdbc driver 24.2.0. Attackers can inject malicious parameters into the JDBC URL, triggering JNDI injection during the process when the JDBC Driver uses this URL to connect to the database. This can further lead to remote code execution vulnerability.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-55551?component-type=maven&component-name=com.exasol%2Fexasol-jdbc&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-55551
* https://gist.github.com/azraelxuemo/9565ec9219e0c3e9afd5474904c39d0f

## Security

* #66: Fixed vulnerability CVE-2024-55551 in dependency `com.exasol:exasol-jdbc:jar:24.1.1:test`

## Dependency Updates

### Exasol Kinesis Connector Extension

#### Compile Dependency Updates

* Updated `com.amazonaws:aws-java-sdk-kinesis:1.12.778` to `1.12.782`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.1` to `7.1.4`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.15` to `0.5.16`
* Updated `org.mockito:mockito-core:5.14.2` to `5.16.1`
* Updated `org.testcontainers:localstack:1.20.3` to `1.20.6`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.5.0` to `5.0.0`
