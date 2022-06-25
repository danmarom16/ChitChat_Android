package com.example.chitchat.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey @NonNull
    private String id;
    private String name;
    private String server;
    private String last;
    private String lastdate;
    private int pic = 0;


    public Contact(String id, String name, String server,
                   String last, String lastdate) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
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

    public String getLast() {
        return last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public int getPic() {
        return pic;
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

    public void setLastMessage(String last) {
        this.last = last;
    }

    public void setLastMessageDate(String lastMessageDate) {
        this.lastdate = lastMessageDate;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void fMessage() {
        if (this.last != null) {
            if (this.last.length() > 10) {
                this.last = this.last.substring(0, 10);
                this.last = this.last + "...";
            }
        }
    }

    public void fTime() { // must be of type: "yyyy-MM-ddTHH:mm:ss.fffffff"
        if (this.lastdate != null) {
            if (this.lastdate.length() > 16) {
                this.lastdate = this.lastdate.substring(11, 16);
            }
        }
    }


}
