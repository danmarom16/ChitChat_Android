package com.example.chitchat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chitchat.R;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.javaclasses.ApiTypeLogin;
import com.example.chitchat.javaclasses.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPageActivity extends AppCompatActivity {

    private ContactAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        api = ContactAPI.getInstance();

        Button toRegistration = findViewById(R.id.toRegistration);
        toRegistration.setOnClickListener(l -> {
            Intent i = new Intent(this, RegisterPageActivity.class);
            startActivity(i);
        });

        EditText username = findViewById(R.id.login_username);
        EditText password = findViewById(R.id.login_password);
        TextView errorMsg = findViewById(R.id.login_errorMsg);
        Button btnLogin = findViewById(R.id.login_btnLogin);

        btnLogin.setOnClickListener(l -> {

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(btnLogin.getWindowToken(), 0);

            Intent i = new Intent(this, ContactsActivity.class);
            ApiTypeLogin loginData = new ApiTypeLogin(
                    username.getText().toString(),
                    password.getText().toString()
            );

            Call<UserData> call = api.getWebServiceApi().login(loginData);
            call.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                    if (response.raw().code() == 200) {
                        api.setLoggedUser(response.body());
                        errorMsg.setText("");
                        startActivity(i);
                    } else {
                        errorMsg.setText(R.string.login_error_msg);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                    errorMsg.setText(R.string.apiFail);
                }
            });
        });
    }
}