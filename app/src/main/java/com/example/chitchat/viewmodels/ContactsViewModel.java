package com.example.chitchat.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.chitchat.R;
import com.example.chitchat.entities.Contact;
import com.example.chitchat.javaclasses.ApiTypeContact;
import com.example.chitchat.repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactsViewModel extends ViewModel {

    private ContactRepository contactRepo;
    private LiveData<List<Contact>> contacts;

    public ContactsViewModel() {
        contactRepo = ContactRepository.getInstance();
        contacts = contactRepo.getAll();
    }

    public LiveData<List<Contact>> get() {
        return contacts;
    }


    public void add(ApiTypeContact apiTypeContact) {
        contactRepo.add(apiTypeContact);
    }

////    public void delete(){contactRepo.delete(contact);};
////    public void reload(){contactRepo.reload();};
}
