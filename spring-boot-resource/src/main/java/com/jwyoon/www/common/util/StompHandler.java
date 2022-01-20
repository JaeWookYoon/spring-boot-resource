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
                // ?œ ??ê°? Websocket?œ¼ë¡? connect()ë¥? ?•œ ?’¤ ?˜¸ì¶œë¨
            	//System.out.println("CONNECT USER : "+userName);
            	System.out.println("Connect : "+accessor.toString());
                break;
            case DISCONNECT:
                // ?œ ??ê°? Websocket?œ¼ë¡? disconnect() ë¥? ?•œ ?’¤ ?˜¸ì¶œë¨ or ?„¸?…˜?´ ?Š?–´ì¡Œì„ ?•Œ ë°œìƒ?•¨(?˜?´ì§? ?´?™~ ë¸Œë¼?š°?? ?‹«ê¸? ?“±)
            	//System.out.println("DISCONNECT USER : "+userName);
            	System.out.println("disConnect : "+accessor.toString());
                break;
            default:
                break;
        }
	}
	
	
}
