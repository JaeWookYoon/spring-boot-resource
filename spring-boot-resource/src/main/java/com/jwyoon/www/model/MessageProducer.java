package com.jwyoon.www.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
@Component
public class MessageProducer {
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	/*
	 * @Value(value="${message.topic}") private String topicName;
	 * 
	 * public void sendMessage(String topic,String message) {
	 * ListenableFuture<SendResult<String,String>> future =
	 * kafkaTemplate.send(topic,message); future.addCallback(new
	 * ListenableFutureCallback<SendResult<String,String>>(){
	 * 
	 * @Override public void onSuccess(SendResult<String, String> result) {
	 * System.out.println("SEND MESSAGE = " + message + " , with offset = " +
	 * result.getRecordMetadata()); }
	 * 
	 * @Override public void onFailure(Throwable ex) {
	 * System.out.println("UNSEND MESSAGE = " + message + " , ERROR = "
	 * +ex.getMessage()); } }); }
	 */
}
