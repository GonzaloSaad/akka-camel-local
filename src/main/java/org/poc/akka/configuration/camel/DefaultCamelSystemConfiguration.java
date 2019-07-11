package org.poc.akka.configuration.camel;

import akka.actor.Props;
import com.amazonaws.services.sqs.AmazonSQS;
import org.poc.akka.camel.consumers.PrinterCamelMessageConsumer;
import org.poc.akka.camel.registry.JMSRegistryProvider;
import org.poc.akka.camel.registry.RegistryProvider;
import org.poc.akka.camel.route.JMSRouteSupplier;
import org.poc.akka.camel.route.RouteSupplier;
import org.poc.akka.configuration.SystemConfiguration;
import org.poc.akka.context.ContextConfiguration;
import org.poc.akka.context.camel.CamelContextConfiguration;
import org.poc.akka.credentials.AWSStaticCredentials;
import org.poc.akka.props.builder.PropsBuilder;
import org.poc.akka.props.provider.PropsProvider;
import org.poc.akka.props.provider.camel.AkkaCamelConsumerPropsProvider;
import org.poc.akka.sqsproviders.SQSClientProvider;
import org.poc.akka.sqsproviders.SQSConnectionFactoryProvider;

import javax.jms.ConnectionFactory;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DefaultCamelSystemConfiguration implements SystemConfiguration {

    private static final String DEFAULT_REF_NAME = "connectionFactory";
    private final String queueName;

    public DefaultCamelSystemConfiguration(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public Props getProps() {
        PropsProvider propsProvider = new AkkaCamelConsumerPropsProvider();
        Consumer consumer = new PrinterCamelMessageConsumer();
        RouteSupplier routeSupplier = new JMSRouteSupplier(queueName, DEFAULT_REF_NAME);
        return new PropsBuilder()
                .withPropsProvider(propsProvider)
                .withConsumer(consumer)
                .withRouteSupplier(routeSupplier)
                .build();
    }

    @Override
    public ContextConfiguration getContextConfiguration() {
        Supplier<AmazonSQS> amazonSQSSupplier = new SQSClientProvider(new AWSStaticCredentials());
        ConnectionFactory connectionFactory = new SQSConnectionFactoryProvider(amazonSQSSupplier).get();
        RegistryProvider registryProvider = new JMSRegistryProvider(connectionFactory, DEFAULT_REF_NAME);
        return new CamelContextConfiguration(registryProvider);
    }
}
