package com.jwyoon.www.common.util;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

public class StompHandler extends ChannelInterceptorAdapter {

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		
        switch (accessor.getCommand()) {
            case CONNECT:
                // ? ??κ°? Websocket?Όλ‘? connect()λ₯? ? ?€ ?ΈμΆλ¨
            	//System.out.println("CONNECT USER : "+userName);
            	System.out.println("Connect : "+accessor.toString());
                break;
            case DISCONNECT:
                // ? ??κ°? Websocket?Όλ‘? disconnect() λ₯? ? ?€ ?ΈμΆλ¨ or ?Έ??΄ ??΄μ‘μ ? λ°μ?¨(??΄μ§? ?΄?~ λΈλΌ?°?? ?«κΈ? ?±)
            	//System.out.println("DISCONNECT USER : "+userName);
            	System.out.println("disConnect : "+accessor.toString());
                break;
            default:
                break;
        }
	}
	
	
}
