package com.example.chitchat.javaclasses;

public class ApiTypeRegister {
    private String id;
    private String name;
    private String password;

    public ApiTypeRegister(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return id;
    }

    public void setUsername(String username) {
        this.id = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
