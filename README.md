# Kinesis Connector Extension

[![Build Status][travis-badge]][travis-link]
[![Coveralls][coveralls-badge]][coveralls-link]
[![GitHub Release][gh-release-badge]][gh-release-link]

Exasol Kinesis Extension provides UDF scripts that allow users to import data
from [Kinesis Streams][kinesis-streams] to an Exasol table.

## Information for users.

* [User Guide](doc/user_guide/user_guide.md)
* [Changelog](doc/changes/changelog.md)

## Information for Contributors

* [Developer Guide][developer-guide]

## Dependencies

### Runtime Dependencies

| Dependency                                  | Purpose                                                         | License            |
|---------------------------------------------|-----------------------------------------------------------------|--------------------|
| [AWS Java SDK][aws-java-sdk]                | Access AWS services via Java API                                | Apache License 2.0 |
| [Exasol Script API][exasol-script-api-link] | Accessing Exasol IMPORT / EXPORT API                            | MIT License        |
| [Exasol Import Export UDF Common][ieudf]    | Common Import Export Libraries                                  | MIT License        |
| [FasterXML/jackson][faster-xml-jackson]     | Parsing JSON files                                              | Apache License 2.0 |
| [Scala Logging Library][scala-logging-link] | Scala logging library wrapping SLF4J                            | Apache License 2.0 |

### Test Dependencies

| Dependency                                  | Purpose                                                         | License            |
|---------------------------------------------|-----------------------------------------------------------------|--------------------|
| [Scalatest][scalatest-link]                 | Testing tool for Scala and Java developers                      | Apache License 2.0 |
| [Scalatest Plus][scalatestplus-link]        | Integration support between Scalatest and Mockito               | Apache License 2.0 |
| [Mockito Core][mockitocore-link]            | Mocking framework for unit tests                                | MIT License        |
| [Exasol Testcontainers][exasol-cont-link]   | Exasol Docker containers for tests                              | MIT License        |
| [Localstack Testcontainers][localstack-link]| A local AWS cloud stack                                         | MIT License        |

### Compiler Plugin Dependencies

These plugins help with project development.

| Plugin Name                                 | Purpose                                                         | License              |
|---------------------------------------------|-----------------------------------------------------------------|----------------------|
| [SBT Coursier][sbt-coursier-link]           | Pure Scala artifact fetching                                    | Apache License 2.0   |
| [SBT Wartremover][sbt-wartremover-link]     | Flexible Scala code linting tool                                | Apache License 2.0   |
| [SBT Wartremover Contrib][sbt-wcontrib-link]| Community managed additional warts for wartremover              | Apache License 2.0   |
| [SBT Assembly][sbt-assembly-link]           | Create fat jars with all project dependencies                   | MIT License          |
| [SBT API Mappings][sbt-apimapping-link]     | A plugin that fetches API mappings for common Scala libraries   | Apache License 2.0   |
| [SBT Scoverage][sbt-scoverage-link]         | Integrates the scoverage code coverage library                  | Apache License 2.0   |
| [SBT Coveralls][sbt-coveralls-link]         | Uploads scala code coverage results to https://coveralls.io     | Apache License 2.0   |
| [SBT Updates][sbt-updates-link]             | Checks Maven and Ivy repositories for dependency updates        | BSD 3-Clause License |
| [SBT Scalafmt][sbt-scalafmt-link]           | A plugin for https://scalameta.org/scalafmt/ formatting         | Apache License 2.0   |
| [SBT Scalastyle][sbt-style-link]            | A plugin for http://www.scalastyle.org/ Scala style checker     | Apache License 2.0   |
| [SBT Dependency Graph][sbt-depgraph-link]   | A plugin for visualizing dependency graph of your project       | Apache License 2.0   |
| [SBT Explicit Dependencies][sbt-expdep-link]| Checks which direct libraries required to compile your code     | Apache License 2.0   |
| [SBT Git][sbt-git-link]                     | A plugin for Git integration, used to version the release jars  | BSD 2-Clause License |

[aws-java-sdk]: https://aws.amazon.com/sdk-for-java/
[exasol-cont-link]: https://github.com/exasol/exasol-testcontainers
[exasol-script-api-link]: https://docs.exasol.com/database_concepts/udf_scripts.htm
[faster-xml-jackson]: https://github.com/FasterXML/jackson
[ieudf]: https://github.com/exasol/import-export-udf-common-scala
[localstack-link]: https://www.testcontainers.org/modules/localstack/
[scala-logging-link]: https://github.com/lightbend/scala-logging
[scalatest-link]: http://www.scalatest.org/
[scalatestplus-link]: https://github.com/scalatest/scalatestplus-mockito
[mockitocore-link]: https://site.mockito.org/
[sbt-coursier-link]: https://github.com/coursier/coursier
[sbt-wartremover-link]: http://github.com/puffnfresh/wartremover
[sbt-wcontrib-link]: http://github.com/wartremover/wartremover-contrib
[sbt-assembly-link]: https://github.com/sbt/sbt-assembly
[sbt-apimapping-link]: https://github.com/ThoughtWorksInc/sbt-api-mappings
[sbt-scoverage-link]: http://github.com/scoverage/sbt-scoverage
[sbt-coveralls-link]: https://github.com/scoverage/sbt-coveralls
[sbt-updates-link]: http://github.com/rtimush/sbt-updates
[sbt-scalafmt-link]: https://github.com/lucidsoftware/neo-sbt-scalafmt
[sbt-style-link]: https://github.com/scalastyle/scalastyle-sbt-plugin
[sbt-depgraph-link]: https://github.com/jrudolph/sbt-dependency-graph
[sbt-git-link]: https://github.com/sbt/sbt-git
[sbt-expdep-link]: https://github.com/cb372/sbt-explicit-dependencies

[travis-badge]: https://img.shields.io/travis/com/exasol/kinesis-connector-extension/master.svg?logo=travis
[travis-link]: https://travis-ci.com/exasol/kinesis-connector-extension
[coveralls-badge]: https://coveralls.io/repos/github/exasol/kinesis-connector-extension/badge.svg?branch=master
[coveralls-link]: https://coveralls.io/github/exasol/kinesis-connector-extension?branch=master
[gh-release-badge]: https://img.shields.io/github/release/exasol/kinesis-connector-extension.svg?logo=github
[gh-release-link]: https://github.com/exasol/kinesis-connector-extension/releases/latest

[kinesis-streams]: https://aws.amazon.com/kinesis/data-streams/
[developer-guide]: https://github.com/exasol/import-export-udf-common-scala/blob/master/doc/development/developer_guide.md