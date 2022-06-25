package com.example.chitchat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitchat.R;
import com.example.chitchat.adapters.ContactsListAdapter;
import com.example.chitchat.api.ContactAPI;
import com.example.chitchat.javaclasses.RecyclerItemClickListener;
import com.example.chitchat.viewmodels.ContactsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;


public class ContactsActivity extends AppCompatActivity {

    ContactAPI api;
    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(
                ContactsActivity.this, instanceIdResult -> {
                    String newToken = instanceIdResult.getToken();
                    api.addToken(newToken);
                }
        );

        api = ContactAPI.getInstance();

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });

        FloatingActionButton btn_addNewCon = findViewById(R.id.btn_addNewCon);
        btn_addNewCon.setOnClickListener(l -> {
            Intent intent = new Intent(this, AddNewContactActivity.class);
            startActivity(intent);
        });


        Intent intent = new Intent(this, ChatActivity.class);
        lstContacts.addOnItemTouchListener(
                new RecyclerItemClickListener(this, lstContacts ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        View contact = lstContacts.getLayoutManager().findViewByPosition(position);

                        TextView id = contact.findViewById(R.id.contactId);
                        TextView displayName = contact.findViewById(R.id.contactName);
                        //ImageView profilePicutre = contact.findViewById(R.id.contactImage);

                        intent.putExtra("id", id.getText().toString());
                        intent.putExtra("displayName", displayName.getText().toString());
                        //i.putExtra("profilePicutre", profilePicutre.getText().toString());

                        startActivity(intent);
                    }
                })
        );
    }

}