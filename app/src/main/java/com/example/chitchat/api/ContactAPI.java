package com.example.chitchat.api;

import androidx.lifecycle.MutableLiveData;

import com.example.chitchat.activities.MyApplication;
import com.example.chitchat.R;
import com.example.chitchat.javaclasses.ApiTypeContact;
import com.example.chitchat.entities.Contact;

import java.util.List;
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
        Call<List<Contact>> call = webServiceApi.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> temp = response.body();
                for (Contact c: temp) {
                    c.setPic(R.drawable.chitchat_logo);
                }
                contacts.postValue(temp);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }
}
