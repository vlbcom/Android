package com.vlbcom.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
private MenuItem doneContactItem, editContactItem, deleteContactItem;
private TextView name, email, phone, address;
private EditText editName, editEmail, editPhone, editAddress;

int position;
Intent intent;
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        name=findViewById(R.id.view_name);
        email=findViewById(R.id.view_email);
        phone=findViewById(R.id.view_phone);
        address=findViewById(R.id.view_address);
        editName=findViewById(R.id.edit_name);
        editEmail=findViewById(R.id.edit_email);
        editPhone=findViewById(R.id.edit_phone);
        editAddress=findViewById(R.id.edit_address);
        intent=getIntent();
        position=intent.getIntExtra("POSITION",-1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        doneContactItem=menu.findItem(R.id.done_edit_item);
        editContactItem=menu.findItem(R.id.edit_contact_item);
       deleteContactItem=menu.findItem(R.id.delete_contact_item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.edit_contact_item:
                editContactItem.setVisible(false);
                doneContactItem.setVisible(true);
                editContact1();
                Toast.makeText(this, "Contact in edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.done_edit_item:
                editContactItem.setVisible(true);
                doneContactItem.setVisible(false);

                Toast.makeText(this, "Contact saving in edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_contact_item:
                Toast.makeText(this, "Contact is removing", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        if(position>=0){
              contact=new StoreProvider(this).getContacts().get(position);
            setViewContact();
        }
        else{
            setResult(RESULT_CANCELED);
             finish();
        }
        super.onStart();


    }
    private void setViewContact(){

        name.setText(contact.getName());
        email.setText(contact.getEmail());
        phone.setText(contact.getPhone());
        address.setText(contact.getAddress());

    }

    private void editContact1(){

        editName.setText(contact.getName());
        editEmail.setText(contact.getEmail());
        editPhone.setText(contact.getPhone());
        editAddress.setText(contact.getAddress());

    }
}
