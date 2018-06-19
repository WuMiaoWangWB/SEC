package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity
{
    SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);
        DBConnection helper;
        helper = new DBConnection(StartActivity.this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("Users",null,null,null,null,null,null);
        if(cursor.getCount()==0)
        {
            ContentValues values = new ContentValues();
            values.put("name","呜喵王");
            values.put("sex","男");
            values.put("age","21");
            values.put("password","666");
            values.put("phone","15957640229");
            db.insert("Users",null,values);
            db.close();
        }
        db = openOrCreateDatabase("HCZSInfo.db",MODE_PRIVATE,null);
        cursor = db.query("Bus",null,null,null,null,null,null);
        if(cursor.getCount()==0)
        {
            ContentValues values1 = new ContentValues();
            values1.put("name","K7");
            values1.put("location","3");
            values1.put("direction","顺");
            db.insert("Bus",null,values1);
            ContentValues values2 = new ContentValues();
            values2.put("name","K7");
            values2.put("location","10");
            values2.put("direction","顺");
            db.insert("Bus",null,values2);
            ContentValues values3 = new ContentValues();
            values3.put("name","K7");
            values3.put("location","18");
            values3.put("direction","顺");
            db.insert("Bus",null,values3);
            ContentValues values4 = new ContentValues();
            values4.put("name","K7");
            values4.put("location","17");
            values4.put("direction","反");
            db.insert("Bus",null,values4);
            ContentValues values5 = new ContentValues();
            values5.put("name","K7");
            values5.put("location","13");
            values5.put("direction","反");
            db.insert("Bus",null,values5);
            ContentValues values6 = new ContentValues();
            values6.put("name","K7");
            values6.put("location","5");
            values6.put("direction","反");
            db.insert("Bus",null,values6);
            db.close();
        }
        db = openOrCreateDatabase("HCZSInfo.db",MODE_PRIVATE,null);
        cursor = db.query("Stops",null,null,null,null,null,null);
        if(cursor.getCount()==0)
        {
            addStop("1","农大西门");
            addStop("2","平山公寓");
            addStop("3","平山新村");
            addStop("4","农大南门");
            addStop("5","东湖村口");
            addStop("6","中职技校");
            addStop("7","临东桥");
            addStop("8","临东小区");
            addStop("9","湖山新村");
            addStop("10","学生公寓");
            addStop("11","中医院");
            addStop("12","东站");
            addStop("13","功臣山路口");
            addStop("14","天目路南口");
            addStop("15","江桥路口");
            addStop("16","锦南新村");
            addStop("17","新五洲大酒店");
            addStop("18","石镜小学");
            addStop("19","万马公寓");
            addStop("20","万马集团");
            addStop("21","丁香苑");
            addStop("22","交通局");
            addStop("23","建设局");
            addStop("24","春天");
            addStop("25","天目高中");
            addStop("26","实验中学");
            addStop("27","太阳城东门");
            addStop("28","临西桥南");
            addStop("29","临西桥北");
            addStop("30","公路段");
            addStop("31","衣锦人家");
            addStop("32","农贸城");
            addStop("33","保通驾校");
            addStop("34","化石林");
            addStop("35","林水山居");
            addStop("36","吴越人家西门");
            addStop("37","吴越人家北门");
            addStop("38","农大西门");
            db.close();
        }
        TimerTask task = new TimerTask() {
            public void run() {
                SharedPreferences tag = getSharedPreferences("singIn", Context.MODE_PRIVATE);
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                if (tag.getString("remember", "").equals("1")) {
                    if (tag.getString("auto", "").equals("1")) {
                        Intent intent1 = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent1);
                    } else {
                        startActivity(intent);
                    }
                } else {
                    startActivity(intent);
                }
                StartActivity.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }

    void addStop(String num, String name)
    {
        ContentValues values = new ContentValues();
        values.put("num",num);
        values.put("name",name);
        db.insert("Stops",null,values);
    }

}

