package com.assignment.model;

public class Message {
    public boolean success;
    public String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
