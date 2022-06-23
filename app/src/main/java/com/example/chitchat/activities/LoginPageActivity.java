package com.example.chitchat.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.chitchat.R;
import com.example.chitchat.javaclasses.ApiTypeLogin;
import com.example.chitchat.viewmodels.ContactsViewModel;

public class LoginPageActivity extends AppCompatActivity {

    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        Button toRegistration = findViewById(R.id.toRegistration);
        toRegistration.setOnClickListener(l -> {
            Intent i = new Intent(this, RegisterPageActivity.class);
            startActivity(i);
        });


        EditText username = findViewById(R.id.login_username);
        EditText password = findViewById(R.id.login_password);
        Button btnLogin = findViewById(R.id.login_btnLogin);

        btnLogin.setOnClickListener(l -> {
            Intent intent = new Intent(this, ContactsActivity.class);
            ApiTypeLogin loginData = new ApiTypeLogin(
                    username.getText().toString(),
                    password.getText().toString()
            );
            if(viewModel.login(loginData)){
                startActivity(intent);
            }
            else {
                Log.d("LoginPage", "Didnt Work");
            }
        });
    }
}