package com.exasol.cloudetl.kinesis;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.*;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

class KinesisTestSetup implements AutoCloseable {

    private static final Logger LOG = Logger.getLogger(KinesisTestSetup.class.getName());
    private final LocalStackContainer container;
    private final AmazonKinesis client;

    private KinesisTestSetup(final LocalStackContainer container, final AmazonKinesis client) {
        this.container = container;
        this.client = client;
    }

    static KinesisTestSetup create() {
        final LocalStackContainer container = new LocalStackContainer(
                DockerImageName.parse(IntegrationTestConstants.LOCALSTACK_DOCKER_IMAGE))
                .withServices(LocalStackContainer.Service.KINESIS).withReuse(false);
        container.start();
        final URI endpoint = container.getEndpointOverride(LocalStackContainer.Service.KINESIS);
        final AWSCredentials credentials = new BasicAWSCredentials(container.getAccessKey(), container.getSecretKey());
        final AmazonKinesis kinesisClient = AmazonKinesisClientBuilder.standard()
                .withEndpointConfiguration(new EndpointConfiguration(endpoint.toString(), container.getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        System.setProperty(SDKGlobalConfiguration.AWS_CBOR_DISABLE_SYSTEM_PROPERTY, "true");
        return new KinesisTestSetup(container, kinesisClient);
    }

    public void createKinesisStream(final String streamName, final int shardsCounter) {
        client.createStream(streamName, shardsCounter);
        LOG.fine("Waiting 30s until stream is created...");
        try {
            // We have to wait until stream is ready to be accessed.
            Thread.sleep(30 * 1000);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Sleep interrupted");
        }
    }

    public void putRecord(final String streamName, final String data, final String partitionKey) {
        final byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        client.putRecord(streamName, ByteBuffer.wrap(bytes), partitionKey);
    }

    @Override
    public void close() {
        this.client.shutdown();
        this.container.close();
    }
}
