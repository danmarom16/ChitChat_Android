package com.example.chitchat.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.dao.AppLocalDB;
import com.example.chitchat.dao.ContactsDao;
import com.example.chitchat.entities.Contact;
import com.example.chitchat.javaclasses.ApiTypeLogin;
import com.example.chitchat.javaclasses.UserData;

import java.util.List;

public class ContactRepository {
    public static ContactRepository repository;

    private AppLocalDB localDB;
    private ContactsDao contactsDao;
    private ContactListData contactListData;
    private ContactAPI api;

    public static ContactRepository getInstance() {
        if (repository == null) {
            repository = new ContactRepository();
        }
        return repository;
    }

    private ContactRepository() {
        localDB = AppLocalDB.getInstance();
        contactsDao = localDB.contactsDao();
        contactListData = new ContactListData();
        api = ContactAPI.getInstance();
    }

    class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();
            setValue(contactsDao.index());
        }

        @Override
        protected void onActive() {
            super.onActive();
            api.get(this);
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add(UserData newContact){
        api.add(newContact, this);
        // alert if failed
    }

    public void reload(){
        api.get(this.contactListData);
    }

    public void insert(UserData userData){
        contactsDao.insert(new Contact(userData.getId(), userData.getName(),
                userData.getServer(),"bla bla", "10:00"));
    }
    public boolean login(ApiTypeLogin loginData){
        return api.login(loginData);
    }
}

