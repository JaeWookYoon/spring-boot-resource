package com.jwyoon.www.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

//@Configuration
public class KafkaConfig {
	@Value(value="${kafka.bootstrapAddress}")
	private String bootStrapAddress;
	@Value(value="${message.topic}")
	private String topicName;
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String,Object> config = new HashMap<>();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
		return new KafkaAdmin(config);
	}
	@Bean
	public NewTopic topic() {
		return new NewTopic(topicName,1,(short)1);
	}	
}
