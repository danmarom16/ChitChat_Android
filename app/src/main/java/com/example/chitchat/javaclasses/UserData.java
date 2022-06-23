package com.example.chitchat.javaclasses;

public class UserData {

    private String id;
    private String name;
    private String server;


    public UserData(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
    }

    public UserData(UserData userData) {
        this.id = userData.getId();
        this.name = userData.getName();
        this.server = userData.getServer();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }
}