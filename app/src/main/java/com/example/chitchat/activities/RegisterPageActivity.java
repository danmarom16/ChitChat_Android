package com.example.chitchat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.chitchat.R;

public class RegisterPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        Button toLogin = findViewById(R.id.toLogin);
        toLogin.setOnClickListener(l -> {
            Intent i = new Intent(this, LoginPageActivity.class);
            startActivity(i);
        });
    }
}