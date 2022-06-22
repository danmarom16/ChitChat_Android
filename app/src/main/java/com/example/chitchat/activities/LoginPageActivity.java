package com.example.chitchat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.chitchat.R;

public class LoginPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Button toRegistration = findViewById(R.id.toRegistration);
        toRegistration.setOnClickListener(l -> {
            Intent i = new Intent(this, RegisterPageActivity.class);
            startActivity(i);
        });
    }
}