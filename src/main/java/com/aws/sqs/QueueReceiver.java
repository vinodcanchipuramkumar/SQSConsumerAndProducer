package com.aws.sqs;

import java.util.ArrayList;
import java.util.List;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

public class QueueReceiver {
	private final String queueName = "PaymentQueue";

	public List<String> getMessages() {
		List<String> messages = new ArrayList<>();

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_SOUTH_1)
				.credentialsProvider(ProfileCredentialsProvider.create("default")).build();
		GetQueueUrlRequest getQueueUrlRequest = GetQueueUrlRequest.builder().queueName(queueName).build();
		String queueUrl = sqsClient.getQueueUrl(getQueueUrlRequest).queueUrl();
		ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder().queueUrl(queueUrl).build();
		List<Message> sqsMessages = sqsClient.receiveMessage(receiveMessageRequest).messages();
		for (Message message : sqsMessages) {
			messages.add(message.body());
		}

		return messages;
	}
}
