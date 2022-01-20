package com.jwyoon.www.common.util;

public class ChatMessage{
    private MessageType type;
    private String content;
    private String sender;
    private String receive;
    //?—´ê±? ???… - 3ê°œì˜ ê°’ì¤‘ ?•˜?‚˜ë§? ê°?ì§? ?ˆ˜ ?ˆ?‹¤. ê·? ?™¸?˜ ê°’ì„ ê°?ì§?ê²? ?˜ë©? ì»´íŒŒ?¼ ?˜¤ë¥˜ê? ë°œìƒ
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive.replace("\n", "").trim();
    }

	@Override
	public String toString() {
		return "ChatMessage [content=" + content + ", sender=" + sender + ", receive=" + receive + "]";
	}

    
    
}
