package org.poc.akka.producer;

import java.util.Map;

public interface QueueProducer {
    String produce(String messageBody, Map<String, String> messageAttributes);
}
