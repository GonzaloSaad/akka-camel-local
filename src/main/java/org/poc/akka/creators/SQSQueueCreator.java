package org.poc.akka.creators;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;

public class SQSQueueCreator implements QueueCreator {

    private final AmazonSQS amazonSQS;

    public SQSQueueCreator(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    @Override
    public String createQueue(String queueName) {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest()
                .withQueueName(queueName);
        CreateQueueResult result = amazonSQS.createQueue(createQueueRequest);
        return result.getQueueUrl();
    }
}


