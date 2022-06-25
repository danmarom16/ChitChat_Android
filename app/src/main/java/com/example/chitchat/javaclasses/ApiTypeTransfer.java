package com.example.chitchat.javaclasses;

public class ApiTypeTransfer {

    private String from;
    private String to;
    private String content;

    public ApiTypeTransfer(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setCon(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getCon() {
        return content;
    }
}
