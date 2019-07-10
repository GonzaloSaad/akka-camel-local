package org.poc.akka.registry;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import org.poc.akka.SQSClientProvider;
import org.apache.camel.impl.SimpleRegistry;

public class JMSRegistryProvider implements RegistryProvider {
    @Override
    public SimpleRegistry getRegistry() {
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                SQSClientProvider.getInstance().getSqsClient());

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("sqsConnectionFactory", connectionFactory);
        return registry;
    }
}
