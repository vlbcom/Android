package com.vlbcom.contacts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputEmail, inputPass;
    private Button btnLogin;
    private String lastEmail, lastPass;
        protected static final int RESULT_LOGOUT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEmail = findViewById(R.id.input_email);
        inputPass = findViewById(R.id.input_pass);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (inputEmail.getText().toString().contains("@") && !inputPass.getText().toString().isEmpty()) {
                    StoreProvider provider = new StoreProvider(this);
                    Intent intent = new Intent();
                    intent.setAction("login.load.user");
                    provider.login(inputEmail.getText().toString(),inputPass.getText().toString());
                    startActivityForResult(intent, 1);
                } else {
                    inputEmail.setText("");
                    inputPass.setText("");
                    inputEmail.setHint("Wrong: is not correct");
                }

                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        StoreProvider provider = new StoreProvider(this);
        if(provider.isAuth()) {
            Intent intent = new Intent();
            intent.setAction("login.load.user");
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_LOGOUT) {
                inputEmail.setText("");
                inputPass.setText("");

            } else if (resultCode == RESULT_CANCELED) {
                finish();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
