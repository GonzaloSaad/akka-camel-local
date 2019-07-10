package org.poc.akka.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import org.poc.akka.QueueURL;
import org.poc.akka.SQSClientProvider;

import java.util.List;
import java.util.Optional;

public class SQSConsumer {

    public void consume() {

        Optional<String> queueURL = QueueURL.getInstance().getQueueURL();

        if (queueURL.isPresent()) {
            AmazonSQS sqs = SQSClientProvider.getInstance().getSqsClient();

            List<Message> messages = sqs.receiveMessage(queueURL.get()).getMessages();
            for (Message m : messages) {
                System.out.println(m.getBody());
                sqs.deleteMessage(queueURL.get(), m.getReceiptHandle());
            }
        }
    }
}
