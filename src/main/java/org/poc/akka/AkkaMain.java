package org.poc.akka;

import akka.actor.ActorSystem;
import akka.camel.Camel;
import akka.camel.CamelExtension;
import org.poc.akka.camel.AkkaCamelConsumer;
import org.poc.akka.registry.JMSRegistryProvider;
import org.poc.akka.sqs.SQSProducer;
import org.poc.akka.sqs.SQSQueueCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AkkaMain {
    public static void main(String[] args) throws InterruptedException {
        createQueueAndProduce();
        final ActorSystem system = ActorSystem.create("helloakka");

        Optional.ofNullable(CamelExtension.get(system))
                .map(Camel::context)
                .ifPresent(context -> context.setRegistry(new JMSRegistryProvider().getRegistry()));

        system.actorOf(AkkaCamelConsumer.props(), "consumer");
        Thread.sleep(10000);
        system.terminate();
    }

    private static void createQueueAndProduce(){
        String queueName = "camelQueue";
        String messageBody = "SuperMario rocks!";
        String attributeName = "TEAM";
        String attributeValue = "SUPER_MARIO";
        Map<String, String> attributes = getAttributes(attributeName, attributeValue);

        SQSQueueCreator queueCreator = new SQSQueueCreator(queueName);
        queueCreator.create();

        SQSProducer producer = new SQSProducer(messageBody, attributes);
        producer.produce();
    }

    private static Map<String, String> getAttributes(String key, String value) {
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put(key, value);
        return attributeMap;
    }
}
