package com.example.chitchat.api;

import com.example.chitchat.entities.ApiTypeContact;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceApi {

    @GET("contacts/peter1")
    Call<List<ApiTypeContact>> getContacts();

    @GET("contacts/{id}")
    Call<List<ApiTypeContact>> getContact(@Path("id")int id);

    @POST("contacts")
    Call<List<ApiTypeContact>> addContact(@Body ApiTypeContact contact, String loggedUser);
}
