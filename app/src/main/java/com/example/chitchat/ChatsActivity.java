package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ChatsActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private ContactDao contactDao;
    private AppDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "FooDB")
                .allowMainThreadQueries().build();
        contactDao = db.contactDao();

        FloatingActionButton btn_addNewCon = findViewById(R.id.btn_addNewCon);
        btn_addNewCon.setOnClickListener(l -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        contacts = contactDao.index();

        ListView lv_chats = findViewById(R.id.lv_chats);
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,
                android.R.layout.activity_list_item,
                contacts);

        lv_chats.setAdapter(adapter);
    }
}