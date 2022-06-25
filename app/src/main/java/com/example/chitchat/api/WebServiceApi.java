package com.example.chitchat.api;


import com.example.chitchat.entities.Contact;
import com.example.chitchat.javaclasses.ApiTypeInvitation;
import com.example.chitchat.javaclasses.ApiTypeLogin;
import com.example.chitchat.javaclasses.ApiTypeMessage;
import com.example.chitchat.javaclasses.ApiTypeRegister;
import com.example.chitchat.javaclasses.ApiTypeTransfer;
import com.example.chitchat.javaclasses.UserData;

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

    @POST("login/")
    Call<UserData> login(@Body ApiTypeLogin loginData);

    @POST("register/")
    Call<UserData> register(@Body ApiTypeRegister registerData);

    @GET("contacts/{contactId}/Messages/{userId}")
    Call<List<ApiTypeMessage>> getMessages(
            @Path(value = "contactId", encoded = true) String contactId,
            @Path(value = "userId", encoded = true) String userId);

    @POST("transfer/")
    Call<Void> sendMessage(@Body ApiTypeTransfer apiTypeTransfer);


}
