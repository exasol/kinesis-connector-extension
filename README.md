# Kinesis Connector Extension

[![Build Status](https://github.com/exasol/kinesis-connector-extension/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/kinesis-connector-extension/actions/workflows/ci-build.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akinesis-connector-extension&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Akinesis-connector-extension)

Exasol Kinesis Extension provides UDF scripts that allow users to import data
from [Kinesis Data Streams (KDS)][kinesis-streams] to an Exasol table.

KDS is a real-time data streaming service provided by Amazon. It lets you
continuously collect and temporarily store data from multiple sources. KDS
provides SDKs for creating custom producing and consuming application.

This connector is a consumer that transfers the data from KDS to an Exasol
table. Since the data in KDS is stored for a limited time you have to schedule
Kinesis Connector to transfer the data regularly to avoid data loss.

## Information for users.

* [User Guide](doc/user_guide/user_guide.md)
* [Changelog](doc/changes/changelog.md)
* [How to import data from AWS Kinesis Data Streams with Exasol (tech blog)][kinesis-blog-link]
* [Dependencies](dependencies.md)

## Information for Contributors

* [Developer Guide][developer-guide]

[kinesis-streams]: https://aws.amazon.com/kinesis/data-streams/
[developer-guide]: https://github.com/exasol/import-export-udf-common-scala/blob/main/doc/development/developer_guide.md
[kinesis-blog-link]: https://community.exasol.com/t5/tech-blog/how-to-import-data-from-aws-kinesis-data-streams-with-our/ba-p/1704
