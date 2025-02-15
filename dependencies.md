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
| [Maven Project Version Getter][23]         | [MIT License][24]                        |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Apache Maven Clean Plugin][25]                         | [Apache-2.0][26]                               |
| [Apache Maven Install Plugin][27]                       | [Apache-2.0][26]                               |
| [Apache Maven Resources Plugin][28]                     | [Apache-2.0][26]                               |
| [Apache Maven Site Plugin][29]                          | [Apache-2.0][26]                               |
| [SonarQube Scanner for Maven][30]                       | [GNU LGPL 3][31]                               |
| [Apache Maven Toolchains Plugin][32]                    | [Apache-2.0][26]                               |
| [Apache Maven Compiler Plugin][33]                      | [Apache-2.0][26]                               |
| [Apache Maven Enforcer Plugin][34]                      | [Apache-2.0][26]                               |
| [Maven Flatten Plugin][35]                              | [Apache Software Licenese][26]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][36] | [ASL2][37]                                     |
| [scala-maven-plugin][38]                                | [Public domain (Unlicense)][39]                |
| [ScalaTest Maven Plugin][40]                            | [the Apache License, ASL Version 2.0][9]       |
| [Apache Maven Javadoc Plugin][41]                       | [Apache-2.0][26]                               |
| [Maven Surefire Plugin][42]                             | [Apache-2.0][26]                               |
| [Project Keeper Maven plugin][43]                       | [The MIT License][44]                          |
| [Scalastyle Maven Plugin][45]                           | [Apache 2.0][46]                               |
| [spotless-maven-plugin][47]                             | [The Apache Software License, Version 2.0][26] |
| [scalafix-maven-plugin][48]                             | [BSD-3-Clause][49]                             |
| [Versions Maven Plugin][50]                             | [Apache License, Version 2.0][26]              |
| [duplicate-finder-maven-plugin Maven Mojo][51]          | [Apache License 2.0][46]                       |
| [Apache Maven Assembly Plugin][52]                      | [Apache-2.0][26]                               |
| [Apache Maven JAR Plugin][53]                           | [Apache-2.0][26]                               |
| [Artifact reference checker and unifier][54]            | [MIT License][55]                              |
| [Maven Failsafe Plugin][56]                             | [Apache-2.0][26]                               |
| [JaCoCo :: Maven Plugin][57]                            | [EPL-2.0][58]                                  |
| [Quality Summarizer Maven Plugin][59]                   | [MIT License][60]                              |
| [error-code-crawler-maven-plugin][61]                   | [MIT License][62]                              |
| [Reproducible Build Maven Plugin][63]                   | [Apache 2.0][37]                               |
| [Exec Maven Plugin][64]                                 | [Apache License 2][26]                         |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][65] | MIT     |

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
[23]: https://github.com/exasol/maven-project-version-getter/
[24]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[25]: https://maven.apache.org/plugins/maven-clean-plugin/
[26]: https://www.apache.org/licenses/LICENSE-2.0.txt
[27]: https://maven.apache.org/plugins/maven-install-plugin/
[28]: https://maven.apache.org/plugins/maven-resources-plugin/
[29]: https://maven.apache.org/plugins/maven-site-plugin/
[30]: http://docs.sonarqube.org/display/PLUG/Plugin+Library/sonar-maven-plugin
[31]: http://www.gnu.org/licenses/lgpl.txt
[32]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[33]: https://maven.apache.org/plugins/maven-compiler-plugin/
[34]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[35]: https://www.mojohaus.org/flatten-maven-plugin/
[36]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[37]: http://www.apache.org/licenses/LICENSE-2.0.txt
[38]: http://github.com/davidB/scala-maven-plugin
[39]: http://unlicense.org/
[40]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[41]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[42]: https://maven.apache.org/surefire/maven-surefire-plugin/
[43]: https://github.com/exasol/project-keeper/
[44]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[45]: http://www.scalastyle.org
[46]: http://www.apache.org/licenses/LICENSE-2.0.html
[47]: https://github.com/diffplug/spotless
[48]: https://github.com/evis/scalafix-maven-plugin
[49]: https://opensource.org/licenses/BSD-3-Clause
[50]: https://www.mojohaus.org/versions/versions-maven-plugin/
[51]: https://basepom.github.io/duplicate-finder-maven-plugin
[52]: https://maven.apache.org/plugins/maven-assembly-plugin/
[53]: https://maven.apache.org/plugins/maven-jar-plugin/
[54]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[55]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[56]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[57]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[58]: https://www.eclipse.org/legal/epl-2.0/
[59]: https://github.com/exasol/quality-summarizer-maven-plugin/
[60]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[61]: https://github.com/exasol/error-code-crawler-maven-plugin/
[62]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[63]: http://zlika.github.io/reproducible-build-maven-plugin
[64]: https://www.mojohaus.org/exec-maven-plugin
[65]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.3.tgz
