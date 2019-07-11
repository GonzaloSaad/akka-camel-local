package org.poc.akka.sqsproviders;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.poc.akka.environment.Environment;

import java.util.function.Supplier;

public class SQSClientProvider implements Supplier<AmazonSQS> {

    private final Supplier<AWSCredentialsProvider> credentialsProvider;

    public SQSClientProvider(Supplier<AWSCredentialsProvider> credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    @Override
    public AmazonSQS get() {
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(getEndpoint())
                .withCredentials(credentialsProvider.get())
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                Environment.getBaseEndpoint(),
                Environment.getRegion()
        );
    }
}
