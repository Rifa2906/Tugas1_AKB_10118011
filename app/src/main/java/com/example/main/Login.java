package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    DatabaseHelper db;
    Button login,register;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);

        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();

                Boolean masuk = db.cekLogin(strUsername,strPassword);
                if (masuk == true){

                    Boolean updatesession = db.upgradeSession("ada",1);
                    if(updatesession == true){
                        Toast.makeText(getApplicationContext(),"Berhasil Masuk",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Gagal Masuk",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}