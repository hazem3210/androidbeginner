package com.example.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName="Login.db";
    private static final int DBVersion=1;
    private static final String DBTable="users";
    private static final String Username="username";
    private static final String Password="password";
    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
    MyDB.execSQL("create Table "+DBTable+"( "+Username+" TEXT primary key, "+ Password +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
    MyDB.execSQL("drop Table if exists users");
    }
    public Boolean inData(String username,String password){
    SQLiteDatabase MyDB=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Username,username);
        cv.put(Password,password);
        long result=MyDB.insert(DBTable,null,cv);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Boolean checkUserName(String username){
        SQLiteDatabase MyDB=this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from "+ DBTable+" where "+Username+" = ?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUserNamePassword(String username,String password){
        SQLiteDatabase MyDB=this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from "+ DBTable+" where "+Username+" = ? and "+Password+" = ?",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
