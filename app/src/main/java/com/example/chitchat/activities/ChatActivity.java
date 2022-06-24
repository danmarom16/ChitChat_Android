package com.example.chitchat.activities;

import static com.example.chitchat.activities.MyApplication.context;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitchat.R;
import com.example.chitchat.adapters.ChatMessagesAdapter;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.javaclasses.ApiTypeMessage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private ContactAPI api;
    private List<ApiTypeMessage> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        api = ContactAPI.getInstance();

        ChatMessagesAdapter adapter = new ChatMessagesAdapter(context,null);
        RecyclerView messageListView = findViewById(R.id.activity_chat__messages);
        messageListView.setLayoutManager(new LinearLayoutManager(this));
        messageListView.setAdapter(adapter);

        TextView contactDisplayName = findViewById(R.id.activity_chat__displayName);
        String contactId = null;

        // String contactProfilePic;
        if (getIntent().getExtras() != null) {
            contactDisplayName.setText(getIntent().getExtras().getString("displayName"));
            contactId =  getIntent().getExtras().getString("id");
            // contactProfilePic = getIntent().getExtras().getString("profilePicutre");
            String loggedUserId = api.getLoggedUser().getId();

            Call<List<ApiTypeMessage>> call = api.getWebServiceApi().getMessages(contactId, loggedUserId);
            call.enqueue(new Callback<List<ApiTypeMessage>>() {
                @Override
                public void onResponse(@NonNull Call<List<ApiTypeMessage>> call, @NonNull Response<List<ApiTypeMessage>> response) {
                    if (response.raw().code() == 200) {
                        adapter.setMessages(response.body());
                    } else {
                        Log.d("fukci","Fadfad");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<ApiTypeMessage>> call, @NonNull Throwable t) {

                }
            });
        }
    }
}