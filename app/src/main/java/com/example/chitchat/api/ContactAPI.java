package com.example.chitchat.api;

import androidx.lifecycle.MutableLiveData;

import com.example.chitchat.MyApplication;
import com.example.chitchat.R;
import com.example.chitchat.entities.ApiTypeContact;
import com.example.chitchat.entities.Contact;
import com.example.chitchat.repositories.ContactRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
    }

    public void get(MutableLiveData<List<Contact>> contacts){
        Call<List<ApiTypeContact>> call = webServiceApi.getContacts();
        call.enqueue(new Callback<List<ApiTypeContact>>() {
            @Override
            public void onResponse(Call<List<ApiTypeContact>> call, Response<List<ApiTypeContact>> response) {
                contacts.postValue(Contact.convertFromApiToContact(response.body()));
            }

            @Override
            public void onFailure(Call<List<ApiTypeContact>> call, Throwable t) {

            }
        });
    }
}
