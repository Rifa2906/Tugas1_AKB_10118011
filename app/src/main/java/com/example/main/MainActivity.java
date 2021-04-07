package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        logout = findViewById(R.id.btn_logout);

        Boolean cekSession = db.cekSession("ada");
        if (cekSession == false){
            Intent LoginIntent = new Intent(MainActivity.this,Login.class);
            startActivity(LoginIntent);
            finish();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updateSession = db.upgradeSession("kosong",1);
                if(updateSession == true){
                    Toast.makeText(getApplicationContext(),"Berhasil Keluar",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}