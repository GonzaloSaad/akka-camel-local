package org.poc.akka.registry;

import com.amazonaws.services.sqs.AmazonSQS;
import org.poc.akka.SQSClientProvider;
import org.apache.camel.impl.SimpleRegistry;

public class SQSRegistryProvider implements RegistryProvider {
    @Override
    public SimpleRegistry getRegistry() {
        AmazonSQS client = SQSClientProvider.getInstance().getSqsClient();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("client", client);
        return registry;
    }
}
