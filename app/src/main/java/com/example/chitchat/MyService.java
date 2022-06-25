package com.example.chitchat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;

import com.example.chitchat.activities.ChatActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if(message.getNotification() != null){
            Map<String, String> data = message.getData();
            String to = data.get("tto");
            String from = data.get("tfrom");
            String content = data.get("tcontent");
            String pushed = "pushed";
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("from", from);
            intent.putExtra("content", content);
            startActivity(intent);
        }
    }
}