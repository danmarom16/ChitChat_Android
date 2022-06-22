package com.example.chitchat.api;

import com.example.chitchat.entities.ApiContact;
import com.example.chitchat.entities.Contact;
import com.google.android.gms.common.api.Api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceApi {

    @GET("contacts/peter1")
    Call<List<ApiContact>> getContacts();

    @GET("contacts/{id}")
    Call<List<ApiContact>> getContact(@Path("id")int id);

    @POST("contacts")
    Call<List<ApiContact>> addContact(@Body ApiContact contact, String loggedUser);
}
