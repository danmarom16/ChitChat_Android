package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chitchat.adapters.ContactsListAdapter;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.entities.Contact;
import com.example.chitchat.viewmodels.ContactsViewModel;

import java.util.ArrayList;
import java.util.List;


public class ContactsActivity extends AppCompatActivity {
    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });

    }

}