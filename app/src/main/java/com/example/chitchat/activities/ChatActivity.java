package com.example.chitchat.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chitchat.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TextView displayName = findViewById(R.id.activity_chat__displayName);
        if (getIntent().getExtras() != null) {
            displayName.setText(getIntent().getExtras().getString("displayName"));
        }
    }
}