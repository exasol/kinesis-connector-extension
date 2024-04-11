<!-- @formatter:off -->
# Dependencies

## Exasol Kinesis Connector Extension

### Compile Dependencies

| Dependency                           | License                          |
| ------------------------------------ | -------------------------------- |
| [Scala Library][0]                   | [Apache-2.0][1]                  |
| [Import Export UDF Common Scala][2]  | [MIT License][3]                 |
| [AWS Java SDK for Amazon Kinesis][4] | [Apache License, Version 2.0][5] |
| [error-reporting-java][6]            | [MIT License][7]                 |

### Test Dependencies

| Dependency                                 | License                                  |
| ------------------------------------------ | ---------------------------------------- |
| [scalatest][8]                             | [the Apache License, ASL Version 2.0][9] |
| [scalatestplus-mockito][10]                | [Apache-2.0][9]                          |
| [mockito-core][11]                         | [MIT][12]                                |
| [Test containers for Exasol on Docker][13] | [MIT License][14]                        |
| [Testcontainers :: Localstack][15]         | [MIT][16]                                |
| [Test Database Builder for Java][17]       | [MIT License][18]                        |
| [Matcher for SQL Result Sets][19]          | [MIT License][20]                        |
| [Extension integration tests library][21]  | [MIT License][22]                        |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                               |
| [Apache Maven Toolchains Plugin][25]                    | [Apache License, Version 2.0][26]              |
| [Apache Maven Compiler Plugin][27]                      | [Apache-2.0][26]                               |
| [Apache Maven Enforcer Plugin][28]                      | [Apache-2.0][26]                               |
| [Maven Flatten Plugin][29]                              | [Apache Software Licenese][26]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][30] | [ASL2][31]                                     |
| [scala-maven-plugin][32]                                | [Public domain (Unlicense)][33]                |
| [ScalaTest Maven Plugin][34]                            | [the Apache License, ASL Version 2.0][9]       |
| [Apache Maven Javadoc Plugin][35]                       | [Apache-2.0][26]                               |
| [Maven Surefire Plugin][36]                             | [Apache-2.0][26]                               |
| [Project Keeper Maven plugin][37]                       | [The MIT License][38]                          |
| [Scalastyle Maven Plugin][39]                           | [Apache 2.0][40]                               |
| [spotless-maven-plugin][41]                             | [The Apache Software License, Version 2.0][26] |
| [scalafix-maven-plugin][42]                             | [BSD-3-Clause][43]                             |
| [Versions Maven Plugin][44]                             | [Apache License, Version 2.0][26]              |
| [duplicate-finder-maven-plugin Maven Mojo][45]          | [Apache License 2.0][40]                       |
| [Apache Maven Assembly Plugin][46]                      | [Apache-2.0][26]                               |
| [Apache Maven JAR Plugin][47]                           | [Apache License, Version 2.0][26]              |
| [Artifact reference checker and unifier][48]            | [MIT License][49]                              |
| [Maven Failsafe Plugin][50]                             | [Apache-2.0][26]                               |
| [JaCoCo :: Maven Plugin][51]                            | [EPL-2.0][52]                                  |
| [error-code-crawler-maven-plugin][53]                   | [MIT License][54]                              |
| [Reproducible Build Maven Plugin][55]                   | [Apache 2.0][31]                               |
| [Exec Maven Plugin][56]                                 | [Apache License 2][26]                         |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][57] | MIT     |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://aws.amazon.com/sdkforjava
[5]: https://aws.amazon.com/apache2.0
[6]: https://github.com/exasol/error-reporting-java/
[7]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[8]: http://www.scalatest.org
[9]: http://www.apache.org/licenses/LICENSE-2.0
[10]: https://github.com/scalatest/scalatestplus-mockito
[11]: https://github.com/mockito/mockito
[12]: https://opensource.org/licenses/MIT
[13]: https://github.com/exasol/exasol-testcontainers/
[14]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[15]: https://java.testcontainers.org
[16]: http://opensource.org/licenses/MIT
[17]: https://github.com/exasol/test-db-builder-java/
[18]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[19]: https://github.com/exasol/hamcrest-resultset-matcher/
[20]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[21]: https://github.com/exasol/extension-manager/
[22]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[23]: http://sonarsource.github.io/sonar-scanner-maven/
[24]: http://www.gnu.org/licenses/lgpl.txt
[25]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[26]: https://www.apache.org/licenses/LICENSE-2.0.txt
[27]: https://maven.apache.org/plugins/maven-compiler-plugin/
[28]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[29]: https://www.mojohaus.org/flatten-maven-plugin/
[30]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[31]: http://www.apache.org/licenses/LICENSE-2.0.txt
[32]: http://github.com/davidB/scala-maven-plugin
[33]: http://unlicense.org/
[34]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: https://maven.apache.org/surefire/maven-surefire-plugin/
[37]: https://github.com/exasol/project-keeper/
[38]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[39]: http://www.scalastyle.org
[40]: http://www.apache.org/licenses/LICENSE-2.0.html
[41]: https://github.com/diffplug/spotless
[42]: https://github.com/evis/scalafix-maven-plugin
[43]: https://opensource.org/licenses/BSD-3-Clause
[44]: https://www.mojohaus.org/versions/versions-maven-plugin/
[45]: https://basepom.github.io/duplicate-finder-maven-plugin
[46]: https://maven.apache.org/plugins/maven-assembly-plugin/
[47]: https://maven.apache.org/plugins/maven-jar-plugin/
[48]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[49]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[50]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[51]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[52]: https://www.eclipse.org/legal/epl-2.0/
[53]: https://github.com/exasol/error-code-crawler-maven-plugin/
[54]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[55]: http://zlika.github.io/reproducible-build-maven-plugin
[56]: https://www.mojohaus.org/exec-maven-plugin
[57]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.1.tgz
