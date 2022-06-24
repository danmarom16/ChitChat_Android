package com.example.chitchat.activities;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chitchat.R;
import com.example.chitchat.api.ContactAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private ContactAPI api;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        api = ContactAPI.getInstance();

        TextView contactDisplayName = findViewById(R.id.activity_chat__displayName);
        String contactId = null;

        // String contactProfilePic;
        if (getIntent().getExtras() != null) {
            contactDisplayName.setText(getIntent().getExtras().getString("displayName"));
            contactId =  getIntent().getExtras().getString("id");
            // contactProfilePic = getIntent().getExtras().getString("profilePicutre");
            String loggedUserId = api.getLoggedUser().getId();

            Call<List<Message>> call = api.getWebServiceApi().getMessages(contactId, loggedUserId);
            call.enqueue(new Callback<List<Message>>() {
                @Override
                public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                    if (response.raw().code() == 200) {
                        // response.body()
                    } else {

                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable t) {

                }
            });
        }
    }
}