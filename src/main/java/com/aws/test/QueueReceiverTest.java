package com.aws.test;

import com.aws.sqs.QueueReceiver;

public class QueueReceiverTest {
	public static void main(String[] args) {
		QueueReceiver queueReceiver = new QueueReceiver();
		queueReceiver.getMessages().forEach(System.out::println);
	}
}
