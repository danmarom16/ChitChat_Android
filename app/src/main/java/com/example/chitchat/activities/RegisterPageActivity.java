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

        TextView errorMsg = findViewById(R.id.register_errorMsg);

        // register logic:
        Button btnRegister = findViewById(R.id.register_btnRegister);
        btnRegister.setOnClickListener(v -> {

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(btnRegister.getWindowToken(), 0);

            EditText username = findViewById(R.id.register_username);
            EditText displayName = findViewById(R.id.register_displayName);
            EditText password = findViewById(R.id.register_password);
            EditText confirmPassword = findViewById(R.id.register_confirmPassword);
            // String Image = findViewById(R.id.register_image);;

            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                errorMsg.setText(R.string.register_pass_not_match);
            } else {
                Intent i = new Intent(this, ContactsActivity.class);
                ApiTypeRegister registerData = new ApiTypeRegister(
                        username.getText().toString(),
                        displayName.getText().toString(),
                        password.getText().toString()
                );
                Call<UserData> call = api.getWebServiceApi().register(registerData);
                call.enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                        if (response.raw().code() == 200) {
                            api.setLoggedUser(response.body());
                            errorMsg.setText("");
                            startActivity(i);
                        } else {
                            errorMsg.setText(R.string.register_error_msg);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                        errorMsg.setText(R.string.apiFail);
                    }
                });
            }
        });
    }
}