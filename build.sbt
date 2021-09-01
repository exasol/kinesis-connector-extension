import com.exasol.cloudetl.sbt.Dependencies
import com.exasol.cloudetl.sbt.Settings
import com.exasol.cloudetl.sbt.IntegrationTestPlugin

lazy val orgSettings = Seq(
  name := "kinesis-connector-extension",
  description := "Exasol Kinesis Connector Extension",
  organization := "com.exasol",
  organizationHomepage := Some(url("http://www.exasol.com"))
)

lazy val buildSettings = Seq(
  scalaVersion := "2.12.14",
  crossScalaVersions := Seq("2.11.12", "2.12.14")
)

lazy val root =
  project
    .in(file("."))
    .settings(moduleName := "exasol-kinesis-connector-extension")
    .settings(version := "1.0.0")
    .settings(orgSettings)
    .settings(buildSettings)
    .settings(Settings.projectSettings(scalaVersion))
    .settings(
      resolvers ++= Dependencies.Resolvers,
      libraryDependencies ++= Dependencies.AllDependencies,
      excludeDependencies ++= Dependencies.ExcludedDependencies
    )
    .enablePlugins(IntegrationTestPlugin, ReproducibleBuildsPlugin)

addCommandAlias("pluginUpdates", ";reload plugins;dependencyUpdates;reload return")
