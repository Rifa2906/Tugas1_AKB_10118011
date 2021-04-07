package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

    DatabaseHelper db;
    Button login,register;
    EditText username,password,passwordConfir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        username = findViewById(R.id.edt_usernameRegis);
        password = findViewById(R.id.edt_passwordRegis);
        passwordConfir = findViewById(R.id.edt_passwordConfirmasi);

        login = findViewById(R.id.btn_Login);
        register = findViewById(R.id.btn_Register);

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                String strPasswordConf = passwordConfir.getText().toString();

                if(strPassword.equals(strPasswordConf)){
                    Boolean daftar = db.insertUser(strUsername,strPassword);
                    if(daftar == true){
                        Toast.makeText(getApplicationContext(),"Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Register.this,Login.class);
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Password Tidak cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}