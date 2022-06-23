package com.example.chitchat.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.chitchat.R;
import com.example.chitchat.javaclasses.ApiTypeInvitation;
import com.example.chitchat.javaclasses.UserData;
import com.example.chitchat.viewmodels.ContactsViewModel;

public class AddNewContactActivity extends AppCompatActivity {

    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        EditText contactID = findViewById(R.id.add_new_contact__contact_id);
        EditText contactServerName = findViewById(R.id.add_new_contact__contact_server_name);
        Button btnAddContact = findViewById(R.id.add_new_contact__btn_save_contact);

        btnAddContact.setOnClickListener(l -> {
            UserData newContact = new UserData(
                    contactID.getText().toString(),
                    contactID.getText().toString(),
                    contactServerName.getText().toString()
            );
            viewModel.add(newContact);
            finish();
        });
    }
}