package com.example.chitchat.api;

import com.example.chitchat.MyApplication;
import com.example.chitchat.R;
import com.example.chitchat.entities.ApiContact;
import com.example.chitchat.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    Retrofit retrofit;
    WebServiceApi webServiceApi;

    public ContactAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
    }

    public void get(){
        Call<List<ApiContact>> call = webServiceApi.getContacts();
        call.enqueue(new Callback<List<ApiContact>>() {
            @Override
            public void onResponse(Call<List<ApiContact>> call, Response<List<ApiContact>> response) {
                List<ApiContact> apiContacts = response.body();
                List<Contact> contacts = Contact.convertFromApiToContact(apiContacts);
            }

            @Override
            public void onFailure(Call<List<ApiContact>> call, Throwable t) {

            }
        });
    }
}
