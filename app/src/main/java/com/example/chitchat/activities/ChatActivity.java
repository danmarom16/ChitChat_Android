package com.example.chitchat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitchat.R;
import com.example.chitchat.adapters.ChatMessagesAdapter;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.javaclasses.ApiTypeMessage;
import com.example.chitchat.javaclasses.ApiTypeTransfer;

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

        ChatMessagesAdapter adapter = new ChatMessagesAdapter(this,messages);
        RecyclerView messageListView = findViewById(R.id.activity_chat__messages);
        messageListView.setLayoutManager(new LinearLayoutManager(this));
        messageListView.setAdapter(adapter);

        TextView contactDisplayName = findViewById(R.id.activity_chat__displayName);
        // ImageView contactProfilePic = findViewById(R.id.activity_chat__profilePicture);
        EditText currentMessage = findViewById(R.id.activity_chat__currentMessage);
        ImageButton sendMessageBtn = findViewById(R.id.activity_chat__btnSendMessage);
        ImageButton backToContactsBtn = findViewById(R.id.activity_chat__btnBack);

        if (getIntent().getExtras() == null) {
            finish();
        }

        contactDisplayName.setText(getIntent().getExtras().getString("displayName"));
        // contactProfilePic.setImageBitmap( ??????? );  function(getIntent().getExtras().getString("profilePicutre"));

        String loggedUserId = api.getLoggedUser().getId();
        String contactId =  getIntent().getExtras().getString("id");

        Call<List<ApiTypeMessage>> getMessagesCall = api.getWebServiceApi().getMessages(contactId, loggedUserId);
        getMessagesCall.enqueue(new Callback<List<ApiTypeMessage>>() {
            @Override
            public void onResponse(@NonNull Call<List<ApiTypeMessage>> call, @NonNull Response<List<ApiTypeMessage>> response) {
                if (response.raw().code() == 200) {
                    messages = response.body();
                    ApiTypeMessage.formatList(messages);
                    adapter.setMessages(messages);
                    messageListView.scrollToPosition(messageListView.getAdapter().getItemCount() - 1);
                } else {
                    Log.d("Chat", "Get messages failed");
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<ApiTypeMessage>> call, @NonNull Throwable t) {
                Log.d("Chat", String.valueOf(R.string.apiFail));
            }
        });

        sendMessageBtn.setOnClickListener(v -> {
            String content = currentMessage.getText().toString();
            if (content.isEmpty()){
                return;
            }
            ApiTypeTransfer apiTypeTransfer = new ApiTypeTransfer(loggedUserId, contactId, content);
            Call<Void> sendMessageCall = api.getWebServiceApi().sendMessage(apiTypeTransfer);
            sendMessageCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    Log.d("Chat", String.valueOf(response.raw().code()));
                    if (response.raw().code() == 201) {
                        ApiTypeMessage m = ApiTypeMessage.createSenderMessage(currentMessage.getText().toString());
                        currentMessage.setText(null);
                        // hide keyboard
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(currentMessage.getWindowToken(), 0);
                        messages.add(m);
                        adapter.setMessages(messages);
                        // scroll to view ?
                    } else {
                        Log.d("Chat", "Message send failed");
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Log.d("Chat", String.valueOf(R.string.apiFail));
                }
            });
        });

        backToContactsBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, ContactsActivity.class);
            startActivity(i);
        });
    }
}