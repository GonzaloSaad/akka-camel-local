package org.poc.akka.producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import java.util.Map;
import java.util.stream.Collectors;

public class SQSQueueProducer implements QueueProducer{

    private final AmazonSQS amazonSQS;
    private final String queueUrl;

    public SQSQueueProducer(AmazonSQS amazonSQS, String queueUrl) {
        this.amazonSQS = amazonSQS;
        this.queueUrl = queueUrl;
    }

    public String produce(String messageBody, Map<String, String> messageAttributes) {
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withMessageAttributes(getMessageAttributes(messageAttributes));
        SendMessageResult sendMessageResult = amazonSQS.sendMessage(request);
        return sendMessageResult.getMessageId();
    }

    private Map<String, MessageAttributeValue> getMessageAttributes(Map<String, String> messageAttributes) {
        return messageAttributes.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> createMessageAttribute(e.getValue())));
    }

    private MessageAttributeValue createMessageAttribute(String value) {
        return new MessageAttributeValue()
                .withStringValue(value)
                .withDataType("String");
    }
}
