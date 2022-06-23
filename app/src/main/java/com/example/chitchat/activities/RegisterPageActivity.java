package com.example.chitchat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chitchat.R;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.javaclasses.ApiTypeRegister;
import com.example.chitchat.javaclasses.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPageActivity extends AppCompatActivity {

    private ContactAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        api = ContactAPI.getInstance();

        Button btnToLogin = findViewById(R.id.toLogin);
        btnToLogin.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginPageActivity.class);
            startActivity(i);
        });

        // register logic:
        Button btnRegister = findViewById(R.id.register_btnRegister);
        btnRegister.setOnClickListener(v -> {
            EditText username = findViewById(R.id.register_username);
            EditText displayName = findViewById(R.id.register_displayName);
            EditText password = findViewById(R.id.register_password);
            EditText confirmPassword = findViewById(R.id.register_confirmPassword);
            // String Image = findViewById(R.id.register_image);;

            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Log.d("password comparison", "Not match");
            } else {
                ApiTypeRegister registerData = new ApiTypeRegister(
                        username.getText().toString(),
                        displayName.getText().toString(),
                        password.getText().toString()
                );
                Intent i = new Intent(this, ContactsActivity.class);
                Call<UserData> call = api.getWebServiceApi().register(registerData);
                call.enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                        if (response.raw().code() == 200) {
                            api.setLoggedUser(response.body());
                            startActivity(i);
                        } else {
                            Log.d("Register Http Request", "Didn't work");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                    }
                });
            }
        });
    }
}