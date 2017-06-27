package com.example.kunalsingh.entreprise.models;

/**
 * Created by kunalsingh on 24/06/17.
 */

public class Message {

    private String message;
    private String time;
    private int type;

    public Message(String message, String time, int type) {
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
