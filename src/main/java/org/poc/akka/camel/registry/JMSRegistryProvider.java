package org.poc.akka.camel.registry;

import org.apache.camel.impl.SimpleRegistry;

import javax.jms.ConnectionFactory;

public class JMSRegistryProvider implements RegistryProvider {

    private final ConnectionFactory connectionFactory;
    private final String referenceName;

    public JMSRegistryProvider(ConnectionFactory connectionFactory, String referenceName) {
        this.connectionFactory = connectionFactory;
        this.referenceName = referenceName;
    }


    @Override
    public SimpleRegistry getRegistry() {
        //SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
        //        new ProviderConfiguration(),
        //        SQSClientProvider.getInstance().getSqsClient());

        SimpleRegistry registry = new SimpleRegistry();
        registry.put(referenceName, connectionFactory);
        return registry;
    }
}
