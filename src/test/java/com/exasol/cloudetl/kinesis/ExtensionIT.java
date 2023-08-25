package com.exasol.cloudetl.kinesis;

import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;

import com.exasol.bucketfs.BucketAccessException;
import com.exasol.cloudetl.kinesis.KinesisTestSetup.KinesisStream;
import com.exasol.dbbuilder.dialects.Table;
import com.exasol.dbbuilder.dialects.exasol.*;
import com.exasol.exasoltestsetup.ExasolTestSetup;
import com.exasol.exasoltestsetup.ExasolTestSetupFactory;
import com.exasol.extensionmanager.client.model.ExtensionsResponseExtension;
import com.exasol.extensionmanager.client.model.InstallationsResponseInstallation;
import com.exasol.extensionmanager.itest.*;
import com.exasol.extensionmanager.itest.builder.ExtensionBuilder;
import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

import junit.framework.AssertionFailedError;

class ExtensionIT {
    private static final Logger LOGGER = Logger.getLogger(ExtensionIT.class.getName());
    private static final String PREVIOUS_VERSION = "2.7.2";
    private static final String PREVIOUS_VERSION_JAR_FILE = "exasol-kinesis-connector-extension-" + PREVIOUS_VERSION
            + ".jar";
    private static final String EXTENSION_ID = "kinesis-connector-extension.js";
    private static final Path EXTENSION_SOURCE_DIR = Paths.get("extension").toAbsolutePath();
    private static final String PROJECT_VERSION = MavenProjectVersionGetter.getCurrentProjectVersion();
    private static final Path ADAPTER_JAR = getAdapterJar();

    private static final String PARTITION_KEY = "partitionKey-1";

    private static ExasolTestSetup exasolTestSetup;
    private static ExtensionManagerSetup setup;
    private static ExtensionManagerClient client;
    private static KinesisTestSetup kinesisSetup;
    private static Connection connection;
    private static ExasolObjectFactory exasolObjectFactory;

    @BeforeAll
    static void setup() throws FileNotFoundException, BucketAccessException, TimeoutException, SQLException {
        exasolTestSetup = new ExasolTestSetupFactory(Paths.get("no-cloud-setup")).getTestSetup();
        setup = ExtensionManagerSetup.create(exasolTestSetup, ExtensionBuilder.createDefaultNpmBuilder(
                EXTENSION_SOURCE_DIR, EXTENSION_SOURCE_DIR.resolve("dist").resolve(EXTENSION_ID)));
        exasolTestSetup.getDefaultBucket().uploadFile(ADAPTER_JAR, ADAPTER_JAR.getFileName().toString());
        client = setup.client();
        kinesisSetup = KinesisTestSetup.create();
        connection = exasolTestSetup.createConnection();
        exasolObjectFactory = new ExasolObjectFactory(connection,
                ExasolObjectConfiguration.builder().withJvmOptions("-Dcom.amazonaws.sdk.disableCbor=true").build());
    }

    private static Path getAdapterJar() {
        final Path jar = Paths.get("target").resolve("exasol-kinesis-connector-extension-" + PROJECT_VERSION + ".jar")
                .toAbsolutePath();
        if (Files.exists(jar)) {
            return jar;
        } else {
            throw new AssertionFailedError("Adapter jar " + jar + " does not exist. Run 'mvn package'.");
        }
    }

    @AfterAll
    static void teardown() throws Exception {
        if (connection != null) {
            connection.close();
        }
        if (setup != null) {
            setup.close();
        }
        if (exasolTestSetup != null) {
            exasolTestSetup.getDefaultBucket().deleteFileNonBlocking(ADAPTER_JAR.getFileName().toString());
            exasolTestSetup.close();
        }
        if (kinesisSetup != null) {
            kinesisSetup.close();
        }
    }

    @AfterEach
    void cleanup() throws SQLException {
        setup.cleanup();
    }

    @Test
    void listExtensions() {
        final List<ExtensionsResponseExtension> extensions = client.getExtensions();
        assertAll(() -> assertThat(extensions, hasSize(1)), //
                () -> assertThat(extensions.get(0).getName(), equalTo("Kinesis Connector Extension")),
                () -> assertThat(extensions.get(0).getCategory(), equalTo("cloud-storage-importer")),
                () -> assertThat(extensions.get(0).getInstallableVersions().get(0).getName(), equalTo(PROJECT_VERSION)),
                () -> assertThat(extensions.get(0).getInstallableVersions().get(0).isLatest(), is(true)),
                () -> assertThat(extensions.get(0).getInstallableVersions().get(0).isDeprecated(), is(false)),
                () -> assertThat(extensions.get(0).getDescription(),
                        equalTo("Exasol Kinesis Extension for accessing Amazon Kinesis Data Streams")));
    }

    @Test
    void getInstallationsReturnsEmptyList() {
        assertThat(client.getInstallations(), hasSize(0));
    }

    @Test
    void getInstallationsReturnsResult() {
        client.install();
        assertThat(client.getInstallations(), contains(
                new InstallationsResponseInstallation().name("Kinesis Connector Extension").version(PROJECT_VERSION)));
    }

    @Test
    void installingWrongVersionFails() {
        client.assertRequestFails(() -> client.install("wrongVersion"),
                equalTo("Installing version 'wrongVersion' not supported, try '" + PROJECT_VERSION + "'."),
                equalTo(400));
        setup.exasolMetadata().assertNoScripts();
    }

    @Test
    void installCreatesScripts() {
        setup.client().install();
        assertScriptsInstalled();
    }

    @Test
    void installingTwiceCreatesScripts() {
        setup.client().install();
        setup.client().install();
        assertScriptsInstalled();
    }

    @Test
    void importWorksAfterInstallation() throws SQLException {
        setup.client().install();
        verifyImportWorks();
    }

    @Test
    void uninstallExtensionWithoutInstallation() throws SQLException {
        assertDoesNotThrow(() -> client.uninstall());
    }

    @Test
    void uninstallExtensionRemovesScripts() throws SQLException {
        client.install();
        client.uninstall();
        setup.exasolMetadata().assertNoScripts();
    }

    @Test
    void uninstallWrongVersionFails() {
        client.assertRequestFails(() -> client.uninstall("wrongVersion"),
                equalTo("Uninstalling version 'wrongVersion' not supported, try '" + PROJECT_VERSION + "'."),
                equalTo(404));
    }

    @Test
    void listingInstancesNotSupported() {
        client.assertRequestFails(() -> client.listInstances(), equalTo("Finding instances not supported"),
                equalTo(404));
    }

    @Test
    void creatingInstancesNotSupported() {
        client.assertRequestFails(() -> client.createInstance(emptyList()), equalTo("Creating instances not supported"),
                equalTo(404));
    }

    @Test
    void deletingInstancesNotSupported() {
        client.assertRequestFails(() -> client.deleteInstance("inst"), equalTo("Deleting instances not supported"),
                equalTo(404));
    }

    @Test
    void getExtensionDetailsInstancesNotSupported() {
        client.assertRequestFails(() -> client.getExtensionDetails(PROJECT_VERSION),
                equalTo("Creating instances not supported"), equalTo(404));
    }

    @Test
    void upgradeFailsWhenNotInstalled() {
        setup.client().assertRequestFails(() -> setup.client().upgrade(),
                "Not all required scripts are installed: Validation failed: Script 'KINESIS_METADATA' is missing, Script 'KINESIS_IMPORT' is missing, Script 'KINESIS_CONSUMER' is missing",
                412);
    }

    @Test
    void upgradeFailsWhenAlreadyUpToDate() {
        setup.client().install();
        setup.client().assertRequestFails(() -> setup.client().upgrade(),
                "Extension is already installed in latest version " + PROJECT_VERSION, 412);
    }

    @Test
    @Disabled("No previous version available yet")
    void upgradeFromPreviousVersion() throws InterruptedException, BucketAccessException, TimeoutException,
            FileNotFoundException, URISyntaxException, SQLException {
        final PreviousExtensionVersion previousVersion = createPreviousVersion();
        previousVersion.prepare();
        previousVersion.install();
        verifyImportWorks();
        assertInstalledVersion("Kinesis Connector Extension", PREVIOUS_VERSION);
        previousVersion.upgrade();
        assertInstalledVersion("Kinesis Connector Extension", PROJECT_VERSION);
        verifyImportWorks();
    }

    private void assertInstalledVersion(final String expectedName, final String expectedVersion) {
        final List<InstallationsResponseInstallation> installations = setup.client().getInstallations();
        final InstallationsResponseInstallation expectedInstallation = new InstallationsResponseInstallation()
                .name(expectedName).version(expectedVersion);
        // The extension is installed twice (previous and current version), so each one returns the same installation.
        assertAll(() -> assertThat(installations, hasSize(2)),
                () -> assertThat(installations.get(0), equalTo(expectedInstallation)),
                () -> assertThat(installations.get(1), equalTo(expectedInstallation)));
    }

    private PreviousExtensionVersion createPreviousVersion() {
        return setup.previousVersionManager().newVersion().currentVersion(PROJECT_VERSION) //
                .previousVersion(PREVIOUS_VERSION) //
                .adapterFileName(PREVIOUS_VERSION_JAR_FILE) //
                .extensionFileName(EXTENSION_ID) //
                .project("kinesis-connector-extension") //
                .build();
    }

    private void verifyImportWorks() {
        final ExasolSchema schema = exasolObjectFactory.createSchema("TESTING_SCHEMA_" + System.currentTimeMillis());
        final String connectionName = "KINESIS_CONNECTION";
        try (final KinesisStream stream = kinesisSetup.createStream("extension-stream", 1)) {
            stream.putRecord(sensorDataPayload(1, "OK"), PARTITION_KEY);
            stream.putRecord(sensorDataPayload(2, "WARN"), PARTITION_KEY);
            final Table targetTable = schema.createTableBuilder("TARGET").column("SENSOR_ID", "INTEGER")
                    .column("STATUS", "VARCHAR(10)").column("KINESIS_SHARD_ID", "VARCHAR(2000)")
                    .column("SHARD_SEQUENCE_NUMBER", "VARCHAR(2000)").build();

            executeStatement("CREATE OR REPLACE CONNECTION " + connectionName + " TO '' USER '' \n" + //
                    "IDENTIFIED BY 'AWS_ACCESS_KEY=" + kinesisSetup.getAccessKey() + ";AWS_SECRET_KEY="
                    + kinesisSetup.getSecretKey() + "'");
            executeKinesisImport(stream, targetTable, connectionName);
            assertQueryResult(
                    "select sensor_id, status, kinesis_shard_id from " + targetTable.getFullyQualifiedName()
                            + " order by sensor_id",
                    table("BIGINT", "VARCHAR", "VARCHAR") //
                            .row(1L, "OK", "shardId-000000000000") //
                            .row(2L, "WARN", "shardId-000000000000") //
                            .matches());
        } finally {
            schema.drop();
        }
    }

    private void executeKinesisImport(final KinesisStream stream, final Table targetTable,
            final String kinesisConnection) {
        executeStatement("OPEN SCHEMA " + ExtensionManagerSetup.EXTENSION_SCHEMA_NAME);
        final String sql = "IMPORT INTO " + targetTable.getFullyQualifiedName() + "\n" + //
                " FROM SCRIPT " + ExtensionManagerSetup.EXTENSION_SCHEMA_NAME + ".KINESIS_CONSUMER WITH\n" + //
                " TABLE_NAME = '" + targetTable.getFullyQualifiedName() + "'\n" + //
                " CONNECTION_NAME = '" + kinesisConnection + "'\n" + //
                " STREAM_NAME = '" + stream.getName() + "'\n" + //
                " AWS_SERVICE_ENDPOINT = '" + kinesisSetup.getEndpoint() + "'\n" + //
                " REGION = '" + kinesisSetup.getRegion() + "'\n" + //
                " MAX_RECORDS_PER_RUN = '10'";
        LOGGER.info("Executing query '" + sql + "'");
        executeStatement(sql);
    }

    private String sensorDataPayload(final int sensor, final String status) {
        final String json = "{'sensor': " + sensor + ", 'status': '" + status + "'}";
        return json.replaceAll("'", "\"");
    }

    private void executeStatement(final String sql) {
        LOGGER.info(() -> "Running statement '" + sql + "'...");
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (final SQLException exception) {
            throw new IllegalStateException("Failed to execute statement '" + sql + "': " + exception.getMessage(),
                    exception);
        }
    }

    private void assertQueryResult(final String sql, final Matcher<ResultSet> matcher) {
        try (Statement statement = connection.createStatement()) {
            final ResultSet result = statement.executeQuery(sql);
            assertThat(result, matcher);
        } catch (final SQLException exception) {
            throw new IllegalStateException("Failed to execute query '" + sql + "': " + exception.getMessage(),
                    exception);
        }
    }

    private void assertScriptsInstalled() {
        setup.exasolMetadata().assertScript(table() //
                .row(setScript("KINESIS_CONSUMER", "com.exasol.cloudetl.kinesis.KinesisImportQueryGenerator")) //
                .row(setScript("KINESIS_IMPORT", "com.exasol.cloudetl.kinesis.KinesisShardDataImporter")) //
                .row(setScript("KINESIS_METADATA", "com.exasol.cloudetl.kinesis.KinesisShardsMetadataReader")) //
                .matches());
    }

    private Object[] setScript(final String name, final String scriptClass) {
        return script(name, "SET", scriptClass);
    }

    private Object[] script(final String name, final String inputType, final String scriptClass) {
        final String comment = "Created by Extension Manager for Kinesis Connector Extension " + PROJECT_VERSION;
        final String jarPath = "/buckets/bfsdefault/default/" + ADAPTER_JAR.getFileName().toString();
        return new Object[] { name, "UDF", inputType, "EMITS", allOf(//
                containsString("%jar " + jarPath + ";"), //
                containsString("%scriptclass " + scriptClass + ";")), //
                comment };
    }
}
