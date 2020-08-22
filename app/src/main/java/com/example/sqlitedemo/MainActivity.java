package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.d(TAG, "Inserting ..");
        db.addContact(new Contact("Leonard", "9100000000"));
        db.addContact(new Contact("Sheldon", "9199999999"));
        db.addContact(new Contact("Rajesh", "9522222222"));
        db.addContact(new Contact("Penny", "9533333333"));
        LogContacts(db);

        //Update Contact Penny
        Contact updateContact = db.getContact("Penny");
        if(updateContact != null) {
            updateContact.setPhoneNumber("911111111");
            db.updateContact(updateContact);
            Log.d(TAG, "Updating ..");
            LogContacts(db);
        }
        else
        {
            Log.d(TAG, "Contact Not Found");
        }

        //Delete Contact
        Contact deleteContact = db.getContact("Sheldon");
        if(deleteContact !=null){
            db.deleteContact(deleteContact);
            Log.d(TAG, "Deleting ..");
            LogContacts(db);

        }
        else
        {
            Log.d(TAG, "Contact Not Found");
        }
    }

    private void LogContacts(DatabaseHandler db)
    {
        // Reading all contacts
        Log.d(TAG, "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d(TAG, log);
        }
    }

}