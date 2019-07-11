package org.poc.akka.camel.registry;

import com.amazonaws.services.sqs.AmazonSQS;
import org.apache.camel.impl.SimpleRegistry;

import java.util.function.Supplier;

public class SQSRegistryProvider implements RegistryProvider {

    private final Supplier<AmazonSQS> amazonSQSSupplier;
    private final String referenceName;

    public SQSRegistryProvider(Supplier<AmazonSQS> amazonSQSSupplier, String referenceName) {
        this.amazonSQSSupplier = amazonSQSSupplier;
        this.referenceName = referenceName;
    }

    @Override
    public SimpleRegistry getRegistry() {
        AmazonSQS client = amazonSQSSupplier.get();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put(referenceName, client);
        return registry;
    }
}
