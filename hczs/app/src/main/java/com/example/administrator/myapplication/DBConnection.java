package com.example.administrator.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnection extends SQLiteOpenHelper
{
    static final String db_name = "HCZSInfo.db";
    static final int db_version = 1;
    static String table_name = "Users";
    static String id = "_id";
    static String name = "name";
    static String sex = "sex";
    static String age = "age";
    static String password = "password";
    static String phone = "phone";
    static String picture = "picture";

    DBConnection(Context context)
    {
        super(context,db_name,null,db_version);
    }

    public void onCreate(SQLiteDatabase database)
    {
        String sql = "CREATE TABLE IF NOT EXISTS "+table_name+" ("
                +id+" INTEGER primary key autoincrement, "
                +name+" text not null, "
                +sex+" text, "
                +age+" text, "
                +password+" text, "
                +phone+" text, "
                +picture+" text"+" );";
        String sql2 = "CREATE TABLE IF NOT EXISTS Bus ("
                +id+" INTEGER primary key autoincrement, "
                +name+" text not null, "
                +" location text, "
                +" direction text );";
        String sql3 = "CREATE TABLE IF NOT EXISTS Stops ("
                +id+" INTEGER primary key autoincrement, "
                +"num text, "
                +name+" text not null );";
        database.execSQL(sql);
        database.execSQL(sql2);
        database.execSQL(sql3);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    { }
}
