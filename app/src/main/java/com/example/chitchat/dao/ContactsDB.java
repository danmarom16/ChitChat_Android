package com.example.chitchat.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.chitchat.entities.Contact;

@Database(entities = {Contact.class}, version = 2)
public abstract class ContactsDB extends RoomDatabase {

    public abstract ContactsDao postDao();

    public static ContactsDB contactDB;
    public static ContactsDB getInstance(Context context){
        if (contactDB == null) {
            contactDB = Room.databaseBuilder(context.getApplicationContext(),
                    ContactsDB.class, "ContactsDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return contactDB;
    }
}
