package com.jwyoon.www.common.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		if(request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest serveltRequest = (ServletServerHttpRequest)request;
			HttpSession session = serveltRequest.getServletRequest().getSession();
			
			Enumeration<String> s = session.getAttributeNames();
			System.out.println(session.toString());
			while (s.hasMoreElements()) {
				String string = (String) s.nextElement();
			}
			if(session != null) {
				attributes.put("sessionId", session.getId());
			
			}
		}
		return true;
	}
	
	
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	
	}
	
	
}
