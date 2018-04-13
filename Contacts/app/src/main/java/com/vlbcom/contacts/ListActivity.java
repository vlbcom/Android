package com.vlbcom.contacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView contactsList;
    private TextView textView;
    private ContactAdapter contactAdapter;
    private MenuItem addContactItem;
    private String userEmail;
    private String userPassword;
    private int contactsCount;
    Intent intent;
    protected final String LAST_LOGIN_FILE = "lastUser";
    private User currentUser;
    protected static final int RESULT_LOGOUT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        contactsList = findViewById(R.id.contacts_list_v);
        textView = findViewById(R.id.isempty_list_v);
        contactAdapter = new ContactAdapter(this);
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact currentContact = (Contact) contactAdapter.getItem(position);
                Intent intent= new Intent();
                intent.setAction("edit.contact.activity");
                intent.putExtra("POSITION",position);
                startActivityForResult(intent,1);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        addContactItem = menu.findItem(R.id.add_contact);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        StoreProvider provider = new StoreProvider(this);
        contactAdapter.setContacts(provider.getContacts());
        contactsList.setAdapter(contactAdapter);
        setIsVisibility(contactAdapter.getCount());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add_contact:
                intent = new Intent();
                intent.setAction("add.contact.tolist");
                startActivityForResult(intent, 1);

                break;
            case R.id.logout_user:
    new StoreProvider(this).logout();
                setResult(RESULT_LOGOUT);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
           contactAdapter.setContacts(new StoreProvider(this).getContacts());
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

        private void setIsVisibility( int size){
            if (size > 0) {
                contactsList.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            } else {
                contactsList.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        }


    }

