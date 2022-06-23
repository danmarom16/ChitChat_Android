package com.example.chitchat.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.chitchat.entities.Contact;
import com.example.chitchat.javaclasses.ApiTypeInvitation;
import com.example.chitchat.javaclasses.ApiTypeLogin;
import com.example.chitchat.javaclasses.UserData;
import com.example.chitchat.repositories.ContactRepository;

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


    public void add(UserData newContact) {
        contactRepo.add(newContact);
        //if failed -> alert
    }

    public boolean login(ApiTypeLogin loginData){

        return contactRepo.login(loginData);
    }

////    public void delete(){contactRepo.delete(contact);};
////    public void reload(){contactRepo.reload();};
}
