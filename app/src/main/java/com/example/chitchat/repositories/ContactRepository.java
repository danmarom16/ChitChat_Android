package com.example.chitchat.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chitchat.R;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.entities.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {
    private ContactListData contactListData;
    private ContactAPI api;

    public ContactRepository() {
        contactListData = new ContactListData();
        api = new ContactAPI();
    }

        class ContactListData extends MutableLiveData<List<Contact>>
        {
            public ContactListData() {
                super();
                List<Contact> contacts = new ArrayList<>();
                contacts.add(new Contact("0","dan","localhost:3000","Hey boy", "10:00"));
                contacts.add(new Contact("0","asi","localhost:3000","byhe", "20:00"));
                contacts.add(new Contact("0","beni","localhost:3000","bhad", "12:00"));
                contacts.add(new Contact("0","connor","localhost:3000","sia", "10:40"));
                contacts.add(new Contact("0","goku","localhost:3000","shalom", "12:10"));
                contacts.add(new Contact("0","naruto","localhost:3000","ahlan", "11:01"));
                contacts.add(new Contact("0","lupi","localhost:3000","lama", "12:54"));
                contacts.add(new Contact("0","travosh","localhost:3000","kaha", "21:00"));
                contacts.add(new Contact("0","wiz","localhost:3000","ze", "22:00"));
                contacts.add(new Contact("0","kofi","localhost:3000","ken", "12:00"));
                setValue(contacts);
            }

            @Override
            protected void onActive() {
                super.onActive();
                ContactAPI contactAPI = new ContactAPI();
                contactAPI.get(this);
            }
        }

        public LiveData<List<Contact>> getAll(){return contactListData;};
    }

