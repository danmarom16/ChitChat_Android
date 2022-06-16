package com.example.chitchat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate=true)
    private int id;
    private String displayName;
    private String serverID;

    Contact(int id, String displayName, String serverID){
        this.id = id;
        this.displayName = displayName;
        this.serverID = serverID;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getServerID() {
        return serverID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", serverID='" + serverID + '\'' +
                '}';
    }
}
