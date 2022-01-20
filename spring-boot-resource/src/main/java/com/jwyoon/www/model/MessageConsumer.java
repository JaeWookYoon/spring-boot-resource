package com.jwyoon.www.model;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
public class MessageConsumer {

	/*
	 * @Value(value="${message.topic}") private String topicName;
	 * 
	 * private CountDownLatch latch = new CountDownLatch(1);
	 * 
	 * @KafkaListener(topics = {"${message.topic}"},groupId = "foo",containerFactory
	 * = "kafkaListenerContainerFactory") public void listenGroup(String message) {
	 * System.out.println("RECEIVED = " + message + " group Id = foo");
	 * latch.countDown(); }
	 * 
	 * public CountDownLatch getLatch() { return latch; }
	 */	
	
}
