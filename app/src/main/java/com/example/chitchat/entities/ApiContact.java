package com.example.chitchat.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ApiContact {
    @PrimaryKey(autoGenerate = true)
    private String id;
    private String name;
    private String server;

    public ApiContact(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
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
