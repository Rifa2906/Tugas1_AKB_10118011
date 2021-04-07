package com.example.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "loginSQLite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text, password text)");
        db.execSQL("INSERT INTO session(id, login)  VALUES (1, 'kosong')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);

    }

    //cek session
    public Boolean cekSession(String sessionValues){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  seesion WHERE login = ?",new String[]{sessionValues});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put("login", sessionValues);
        long update = db.update("session",cv,"id"+id,null);
        if (update == -1){
            return false;
        }else {
            return true;
        }

    }

    //insert user
    public Boolean insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        long insert = db.insert("user",null,cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    //cek login
    public  Boolean cekLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?",new String[]{username,password});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
}
