package com.jwyoon.www.common.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageHandler {
	
	@Autowired
	private SimpMessageSendingOperations messgaingTemplate;
	
	@MessageMapping("/protected/renewal")//	/app/protected/renewal
	public void renewalFrg(StompHeaderAccessor accessor , @Payload Map<String,String> coins) {
		String coin = coins.get("coin").trim();		

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("code",coin);

		messgaingTemplate.convertAndSend("/topic/coin", dataMap);
	}
}