package com.example.homerental;

public class Message {
    private String content;
    private boolean isFromUser;
    
    public Message(String content, boolean isFromUser) {
        this.content = content;
        this.isFromUser = isFromUser;
    }
    
    public String getContent() {
        return content;
    }
    
    public boolean isFromUser() {
        return isFromUser;
    }
}