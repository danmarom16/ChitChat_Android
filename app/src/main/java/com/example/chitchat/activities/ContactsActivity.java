package com.example.chitchat.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.chitchat.R;
import com.example.chitchat.adapters.ContactsListAdapter;
import com.example.chitchat.dao.AppLocalDB;
import com.example.chitchat.dao.ContactsDao;
import com.example.chitchat.viewmodels.ContactsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ContactsActivity extends AppCompatActivity {

    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });

        FloatingActionButton btn_addNewCon = findViewById(R.id.btn_addNewCon);
        btn_addNewCon.setOnClickListener(l -> {
            Intent intent = new Intent(this, AddNewContactActivity.class);
            startActivity(intent);
        });

    }

}