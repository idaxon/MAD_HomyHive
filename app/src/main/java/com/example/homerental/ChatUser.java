package com.example.homerental;

public class ChatUser {
    private String name;
    private String lastMessage;
    private String timestamp;

    public ChatUser(String name, String lastMessage, String timestamp) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }
}