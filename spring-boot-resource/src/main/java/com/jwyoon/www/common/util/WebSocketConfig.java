package com.jwyoon.www.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer{
		
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic" , "/queue"); 
		config.setApplicationDestinationPrefixes("/app"); 		
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {		
		registry.addEndpoint("/secured")
		.setAllowedOrigins("*")
		.withSockJS().setInterceptors(httpHandshakeInterceptor());		
	}
	@Override
	protected void customizeClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(stompHandler());
	}

	@Bean
	public HttpHandshakeInterceptor httpHandshakeInterceptor() {
		return new HttpHandshakeInterceptor();
	}
	@Bean 
	StompHandler stompHandler() {
		return new StompHandler();
	}
	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}

	@Override
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		messages
		.simpDestMatchers("/app/protected/*").authenticated()
		.anyMessage().permitAll();
	}
	
}