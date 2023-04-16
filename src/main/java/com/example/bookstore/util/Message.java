package com.example.bookstore.util;

import net.sf.json.JSONObject;

public class Message {
    public String message;
    public JSONObject info;

    public Message(String message) {
        this.message = message;
    }

    public Message(String message, JSONObject data){
        this.message = message;
        this.info = data;
    }
}
