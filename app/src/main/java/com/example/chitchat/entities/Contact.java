package com.example.chitchat.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.chitchat.R;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private String id;
    private String name;
    private String server;
    private String lastMessage;
    private String lastMessageDate;
    private int pic;
    public Contact(String id, String name, String server,
                   String lastMessage, String lastMessageDate) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        this.pic = 0;
    }

    public Contact(String id, String name, String server,
                   String lastMessage, String lastMessageDate, int pic) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        this.pic = pic;
    }

    public static List<Contact>  convertFromApiToContact(List<ApiContact> apiContacts){
        List<Contact> contactList = new ArrayList<>();
        for (ApiContact apicontact: apiContacts) {
            contactList.add(new Contact(apicontact.getId(), apicontact.getName(),
                    apicontact.getServer(),"Hey boy", "10:00", R.drawable.chitchat_logo));
        }
        return contactList;
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

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastMessageDate() {
        return lastMessageDate;
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
