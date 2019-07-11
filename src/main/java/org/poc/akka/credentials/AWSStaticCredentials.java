package org.poc.akka.credentials;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import java.util.function.Supplier;

public class AWSStaticCredentials implements Supplier<AWSCredentialsProvider> {

    private final String awsSecret;
    private final String awsKey;

    public AWSStaticCredentials(String awsSecret, String awsKey) {
        this.awsSecret = awsSecret;
        this.awsKey = awsKey;
    }

    public AWSStaticCredentials() {
        this("SECRET", "KEY");
    }

    @Override
    public AWSCredentialsProvider get() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsKey, awsSecret));
    }
}
