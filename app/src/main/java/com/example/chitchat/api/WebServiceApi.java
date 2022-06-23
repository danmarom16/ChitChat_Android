package com.example.chitchat.api;

import com.example.chitchat.entities.Contact;
import com.example.chitchat.javaclasses.ApiTypeContact;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceApi {

    @GET("contacts/peter1")
    Call<List<Contact>> getContacts();

    @GET("contacts/{id}")
    Call<List<Contact>> getContact(@Path("id")int id);

    @POST("contacts/peter1")
    Call<Void> addContact(@Body ApiTypeContact contact);
}
