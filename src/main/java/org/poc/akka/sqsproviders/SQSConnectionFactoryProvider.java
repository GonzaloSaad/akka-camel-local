package org.poc.akka.sqsproviders;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;

import java.util.function.Supplier;

public class SQSConnectionFactoryProvider implements Supplier<SQSConnectionFactory> {

    private final Supplier<AmazonSQS> amazonSQSSupplier;

    public SQSConnectionFactoryProvider(Supplier<AmazonSQS> amazonSQSSupplier) {
        this.amazonSQSSupplier = amazonSQSSupplier;
    }

    @Override
    public SQSConnectionFactory get() {
        return new SQSConnectionFactory(
                new ProviderConfiguration(),
                amazonSQSSupplier.get());
    }
}
