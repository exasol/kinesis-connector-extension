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
| [mockito-core][11]                         | [The MIT License][12]                    |
| [Test containers for Exasol on Docker][13] | [MIT License][14]                        |
| [Testcontainers :: Localstack][15]         | [MIT][16]                                |
| [Test Database Builder for Java][17]       | [MIT License][18]                        |
| [Matcher for SQL Result Sets][19]          | [MIT License][20]                        |
| [Extension integration tests library][21]  | [MIT License][22]                        |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                               |
| [Apache Maven Compiler Plugin][25]                      | [Apache-2.0][26]                               |
| [Apache Maven Enforcer Plugin][27]                      | [Apache-2.0][26]                               |
| [Maven Flatten Plugin][28]                              | [Apache Software Licenese][26]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][29] | [ASL2][30]                                     |
| [scala-maven-plugin][31]                                | [Public domain (Unlicense)][32]                |
| [ScalaTest Maven Plugin][33]                            | [the Apache License, ASL Version 2.0][9]       |
| [Apache Maven Javadoc Plugin][34]                       | [Apache-2.0][26]                               |
| [Maven Surefire Plugin][35]                             | [Apache-2.0][26]                               |
| [Versions Maven Plugin][36]                             | [Apache License, Version 2.0][26]              |
| [duplicate-finder-maven-plugin Maven Mojo][37]          | [Apache License 2.0][38]                       |
| [Apache Maven Assembly Plugin][39]                      | [Apache-2.0][26]                               |
| [Apache Maven JAR Plugin][40]                           | [Apache License, Version 2.0][26]              |
| [Artifact reference checker and unifier][41]            | [MIT License][42]                              |
| [Maven Failsafe Plugin][43]                             | [Apache-2.0][26]                               |
| [JaCoCo :: Maven Plugin][44]                            | [Eclipse Public License 2.0][45]               |
| [error-code-crawler-maven-plugin][46]                   | [MIT License][47]                              |
| [Reproducible Build Maven Plugin][48]                   | [Apache 2.0][30]                               |
| [Project keeper maven plugin][49]                       | [The MIT License][50]                          |
| [Scalastyle Maven Plugin][51]                           | [Apache 2.0][38]                               |
| [spotless-maven-plugin][52]                             | [The Apache Software License, Version 2.0][26] |
| [scalafix-maven-plugin][53]                             | [BSD-3-Clause][54]                             |
| [Exec Maven Plugin][55]                                 | [Apache License 2][26]                         |
| [Maven Clean Plugin][56]                                | [The Apache Software License, Version 2.0][30] |
| [Maven Resources Plugin][57]                            | [The Apache Software License, Version 2.0][30] |
| [Maven Install Plugin][58]                              | [The Apache Software License, Version 2.0][30] |
| [Maven Deploy Plugin][59]                               | [The Apache Software License, Version 2.0][30] |
| [Maven Site Plugin 3][60]                               | [The Apache Software License, Version 2.0][30] |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][61] | MIT     |

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
[12]: https://github.com/mockito/mockito/blob/main/LICENSE
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
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[26]: https://www.apache.org/licenses/LICENSE-2.0.txt
[27]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[28]: https://www.mojohaus.org/flatten-maven-plugin/
[29]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[30]: http://www.apache.org/licenses/LICENSE-2.0.txt
[31]: http://github.com/davidB/scala-maven-plugin
[32]: http://unlicense.org/
[33]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[34]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[35]: https://maven.apache.org/surefire/maven-surefire-plugin/
[36]: https://www.mojohaus.org/versions/versions-maven-plugin/
[37]: https://basepom.github.io/duplicate-finder-maven-plugin
[38]: http://www.apache.org/licenses/LICENSE-2.0.html
[39]: https://maven.apache.org/plugins/maven-assembly-plugin/
[40]: https://maven.apache.org/plugins/maven-jar-plugin/
[41]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[42]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[43]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[44]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[45]: https://www.eclipse.org/legal/epl-2.0/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin/
[47]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[48]: http://zlika.github.io/reproducible-build-maven-plugin
[49]: https://github.com/exasol/project-keeper/
[50]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[51]: http://www.scalastyle.org
[52]: https://github.com/diffplug/spotless
[53]: https://github.com/evis/scalafix-maven-plugin
[54]: https://opensource.org/licenses/BSD-3-Clause
[55]: https://www.mojohaus.org/exec-maven-plugin
[56]: http://maven.apache.org/plugins/maven-clean-plugin/
[57]: http://maven.apache.org/plugins/maven-resources-plugin/
[58]: http://maven.apache.org/plugins/maven-install-plugin/
[59]: http://maven.apache.org/plugins/maven-deploy-plugin/
[60]: http://maven.apache.org/plugins/maven-site-plugin/
[61]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.3.1.tgz
