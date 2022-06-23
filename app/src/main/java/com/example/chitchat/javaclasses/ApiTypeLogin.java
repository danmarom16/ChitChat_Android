package com.example.chitchat.javaclasses;

public class ApiTypeLogin {
    private String id;
    private String password;

    public ApiTypeLogin(String username, String password) {
        this.id = username;
        this.password = password;
    }

    public String getUsername() {
        return id;
    }

    public void setUsername(String username) {
        this.id = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
