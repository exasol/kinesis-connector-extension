package com.exasol.cloudetl.kinesis;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.*;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.DescribeStreamSummaryRequest;
import com.amazonaws.services.kinesis.model.DescribeStreamSummaryResult;

class KinesisTestSetup implements AutoCloseable {

    private static final Logger LOG = Logger.getLogger(KinesisTestSetup.class.getName());
    private final LocalStackContainer container;
    private final AmazonKinesis client;

    private KinesisTestSetup(final LocalStackContainer container, final AmazonKinesis client) {
        this.container = container;
        this.client = client;
    }

    static KinesisTestSetup create() {
        @SuppressWarnings("resource") // Container will be stopped in close() method
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

    public KinesisStream createStream(final String streamName, final int shardsCounter) {
        client.createStream(streamName, shardsCounter);
        waitForStreamStatus(streamName, "ACTIVE", Duration.ofSeconds(30));
        return new KinesisStream(streamName, client);
    }

    private void waitForStreamStatus(final String streamName, final String expectedStatus, final Duration waitingTime) {
        final Instant start = Instant.now();
        LOG.fine("Waiting at most " + waitingTime + " for status " + expectedStatus + " of stream " + streamName);
        while (true) {
            final String status = getStreamStatus(streamName);
            LOG.finest("Stream " + streamName + " has status " + status);
            if (status.equals(expectedStatus)) {
                return;
            }
            final Instant now = Instant.now();
            if (now.minus(waitingTime).isAfter(start)) {
                throw new IllegalStateException("Stream " + streamName + " still has status " + status + " after "
                        + Duration.between(start, now));
            }
            sleep(Duration.ofSeconds(1));
        }
    }

    @SuppressWarnings("java:S2925") // Sleep is required here
    private void sleep(final Duration sleepDuration) {
        try {
            Thread.sleep(sleepDuration.toMillis());
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Sleep interrupted");
        }
    }

    private String getStreamStatus(final String streamName) {
        final DescribeStreamSummaryResult summary = client
                .describeStreamSummary(new DescribeStreamSummaryRequest().withStreamName(streamName));
        return summary.getStreamDescriptionSummary().getStreamStatus();
    }

    @Override
    public void close() {
        this.client.shutdown();
        this.container.close();
    }

    public static class KinesisStream implements AutoCloseable {

        private final String streamName;
        private final AmazonKinesis client;

        private KinesisStream(final String streamName, final AmazonKinesis client) {
            this.streamName = streamName;
            this.client = client;
        }

        public void putRecord(final String data, final String partitionKey) {
            final byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            client.putRecord(streamName, ByteBuffer.wrap(bytes), partitionKey);
        }

        @Override
        public void close() {
            client.deleteStream(streamName);
        }

        public String getName() {
            return streamName;
        }
    }

    public String getRegion() {
        return container.getRegion();
    }

    public String getEndpoint() {
        final String endpointConfiguration = container.getEndpointOverride(LocalStackContainer.Service.KINESIS)
                .toString();
        final String modifiedEndpoint = endpointConfiguration.replaceAll("127.0.0.1",
                IntegrationTestConstants.DOCKER_IP_ADDRESS);
        LOG.fine("Got endpoint override '" + endpointConfiguration + "' from container, using modified endpoint "
                + modifiedEndpoint);
        return modifiedEndpoint;
    }

    public String getAccessKey() {
        return container.getAccessKey();
    }

    public String getSecretKey() {
        return container.getSecretKey();
    }
}
