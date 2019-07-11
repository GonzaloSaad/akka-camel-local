package org.poc.akka;

import com.amazonaws.services.sqs.AmazonSQS;
import org.poc.akka.akka.AkkaSystemRunner;
import org.poc.akka.configuration.camel.DefaultCamelSystemConfiguration;
import org.poc.akka.creators.QueueCreator;
import org.poc.akka.creators.SQSQueueCreator;
import org.poc.akka.credentials.AWSStaticCredentials;
import org.poc.akka.producer.QueueProducer;
import org.poc.akka.producer.SQSQueueProducer;
import org.poc.akka.sqsproviders.SQSClientProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String queueName = "queueCamel";

        String message = "Super mario Rocks!";
        Map<String, String> attributes = new HashMap<>();
        attributes.put("TEAM", "SUPERMARIO");

        AmazonSQS amazonSQS = new SQSClientProvider(new AWSStaticCredentials()).get();

        QueueCreator queueCreator = new SQSQueueCreator(amazonSQS);
        String queueUrl = queueCreator.createQueue(queueName);

        QueueProducer queueProducer = new SQSQueueProducer(amazonSQS, queueUrl);
        for (int i = 0; i < 10; i++) {
            queueProducer.produce(message, attributes);
        }

        AkkaSystemRunner runner = new AkkaSystemRunner(new DefaultCamelSystemConfiguration(queueName));
        runner.run();
    }
}
