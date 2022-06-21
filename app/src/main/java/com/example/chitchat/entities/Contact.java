package com.example.chitchat.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String server;
    private String lastMessage;
    private String lastMessageDate;
    private int pic;
    public Contact(int id, String name, String server,
                   String lastMessage, String lastMessageDate) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        this.pic = 0;
    }

    public Contact(int id, String name, String server,
                   String lastMessage, String lastMessageDate, int pic) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastMessageDate() {
        return lastMessageDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setLastMessageDate(String lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
