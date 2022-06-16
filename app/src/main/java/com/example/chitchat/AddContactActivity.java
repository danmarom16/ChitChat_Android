package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "FooDB")
                .allowMainThreadQueries().build();
        contactDao = db.contactDao();

        Button btn_saveNewCon = findViewById(R.id.btn_saveNewCon);
        btn_saveNewCon.setOnClickListener(l ->{
            EditText addNewCon_displayName = findViewById(R.id.addNewCon_DisplayName);
            EditText addNewCon_serverName = findViewById(R.id.addNewCon_ServerName);
            Contact newCon = new Contact(0, addNewCon_displayName.getText().toString(),
                    addNewCon_serverName.getText().toString());
            contactDao.insert();

            finish();
        });
    }
}