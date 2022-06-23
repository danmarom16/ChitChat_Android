package com.example.chitchat.api;

import com.example.chitchat.entities.Contact;
import com.example.chitchat.javaclasses.ApiTypeInvitation;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceApi {

    @GET("contacts/{userId}")
    Call<List<Contact>> getContacts(@Path(value = "userId", encoded = true) String userId);

    @GET("contacts/{id}")
    Call<List<Contact>> getContact(@Path("id")int id);

    @POST("invitations/")
    Call<Void> invite(@Body ApiTypeInvitation invitation);
}
