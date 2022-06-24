package com.example.chitchat.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chitchat.R;
import com.example.chitchat.activities.MyApplication;
import com.example.chitchat.entities.Contact;
import com.example.chitchat.javaclasses.ApiTypeInvitation;
import com.example.chitchat.javaclasses.UserData;
import com.example.chitchat.repositories.ContactRepository;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {

    private UserData loggedUser = null;
    private Retrofit retrofit;
    private WebServiceApi webServiceApi;
    public static ContactAPI contactAPI;

    public static ContactAPI getInstance(){
        if (contactAPI == null){
            contactAPI = new ContactAPI();
        }
        return contactAPI;
    }

    private ContactAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceApi = retrofit.create(WebServiceApi.class);
    }

    public WebServiceApi getWebServiceApi(){
        return webServiceApi;
    }

    public void get(MutableLiveData<List<Contact>> contacts){
        Call<List<Contact>> call = webServiceApi.getContacts(loggedUser.getId());
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

    public void add(UserData newContact, ContactRepository repo){
        ApiTypeInvitation invitation = new ApiTypeInvitation(
                loggedUser.getId(),
                newContact.getId(),
                newContact.getServer()
        );
        Call<Void> call = webServiceApi.invite(invitation);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.raw().code() == 200){
                    repo.reload();
                    //repo.insert(newContact);
                }
                else{
                    Log.d("Contact Api","Cannot add user\n");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void setLoggedUser(UserData userData){
        loggedUser = new UserData(userData);
    }

    public UserData getLoggedUser(){
        return this.loggedUser;
    }
}
