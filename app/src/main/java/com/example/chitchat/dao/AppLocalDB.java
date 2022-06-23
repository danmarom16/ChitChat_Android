package com.example.chitchat.dao;

import static com.example.chitchat.activities.MyApplication.context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.chitchat.entities.Contact;

@Database(entities = {Contact.class}, version = 3)
public abstract class AppLocalDB extends RoomDatabase {

    public abstract ContactsDao contactsDao();
    public static AppLocalDB localDB;

    public static AppLocalDB getInstance() {
        if (localDB == null) {
            localDB = Room.databaseBuilder(context.getApplicationContext(),
                    AppLocalDB.class, "ContactsDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return localDB;
    }
}
