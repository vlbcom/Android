package com.vlbcom.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class AddActivity extends AppCompatActivity {
    private MenuItem doneContactItem;
    private EditText addEmail, addName, addPhone, addAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addName = findViewById(R.id.add_name);
        addEmail = findViewById(R.id.add_email);
        addPhone = findViewById(R.id.add_phone);
        addAddress = findViewById(R.id.add_address);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        doneContactItem = findViewById(R.id.done_item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done_item:
                returnNewContact();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void returnNewContact() {

        StoreProvider provider = new StoreProvider(this);
        provider.add(new Contact(addName.getText().toString(),
                addEmail.getText().toString(),
                addPhone.getText().toString(),
                addAddress.getText().toString()));
        setResult(RESULT_OK);
        finish();
    }

}
