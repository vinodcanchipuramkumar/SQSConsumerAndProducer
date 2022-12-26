package com.aws.sqs;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class QueueSender {
	private final String queueName = "PaymentQueue";

	public void sendMessage(String message) {
		SqsClient sqsClient = SqsClient.builder().region(Region.AP_SOUTH_1)
				.credentialsProvider(ProfileCredentialsProvider.create("default")).build();
		CreateQueueRequest request = CreateQueueRequest.builder().queueName(queueName).build();
		sqsClient.createQueue(request);

		GetQueueUrlRequest queueUrlRequest = GetQueueUrlRequest.builder().queueName(queueName).build();
		String queueUrl = sqsClient.getQueueUrl(queueUrlRequest).queueUrl();

		SendMessageRequest sendMessageRequest = SendMessageRequest.builder().queueUrl(queueUrl).messageBody(message)
				.delaySeconds(2).build();
		sqsClient.sendMessage(sendMessageRequest);
		System.out.println("Message Send......");
	}
}
