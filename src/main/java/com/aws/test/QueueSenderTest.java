package com.aws.test;

import com.aws.sqs.QueueSender;

public class QueueSenderTest {
	public static void main(String[] args) {
		QueueSender queueSender = new QueueSender();
		queueSender.sendMessage("{MerchantId: 93839, amount: 3833}");
	}
}
