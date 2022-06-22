package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chitchat.adapters.ContactsListAdapter;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.entities.Contact;

import java.util.ArrayList;
import java.util.List;


public class ChatsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        ContactAPI contactAPI = new ContactAPI();
        contactAPI.get();

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("0","dan","localhost:3000","Hey boy", "10:00", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","asi","localhost:3000","byhe", "20:00", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","beni","localhost:3000","bhad", "12:00", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","connor","localhost:3000","sia", "10:40", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","goku","localhost:3000","shalom", "12:10", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","naruto","localhost:3000","ahlan", "11:01", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","lupi","localhost:3000","lama", "12:54", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","travosh","localhost:3000","kaha", "21:00", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","wiz","localhost:3000","ze", "22:00", R.drawable.chitchat_logo));
        contacts.add(new Contact("0","kofi","localhost:3000","ken", "12:00", R.drawable.chitchat_logo));

        adapter.setContacts(contacts);
    }

}