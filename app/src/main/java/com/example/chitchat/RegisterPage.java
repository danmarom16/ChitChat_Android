package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        Button toLogin = findViewById(R.id.toLogin);
        toLogin.setOnClickListener(l -> {
            Intent i = new Intent(this, LoginPage.class);
            startActivity(i);
        });
    }
}