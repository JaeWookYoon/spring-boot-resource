package com.jwyoon.www.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

//@EnableKafka
//@Configuration
public class KafkaConsumerConfig {

	@Value(value="${kafka.bootstrapAddress}")
	private String bootStrapAddress;
	
	public ConsumerFactory<String, String> consumerFactory(String groupId){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId){
		ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();//translate here Use Generic!
		factory.setConsumerFactory(consumerFactory(groupId));
		return factory;
	}
	public ConcurrentKafkaListenerContainerFactory<String, String> fookafkaListenerContainerFactory(String groupId){
		
		return kafkaListenerContainerFactory("foo");
	}
	
}
