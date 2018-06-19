package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
{
    EditText edt01,edt02;
    CheckBox cb01,cb02;
    Button btn;
    SharedPreferences signin;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signin);
        edt01 = (EditText)findViewById(R.id.et_zh);
        edt02 = (EditText)findViewById(R.id.et_mm);
        cb01 = (CheckBox)findViewById(R.id.cb_01);
        cb02 = (CheckBox)findViewById(R.id.cb_02);
        btn = (Button)findViewById(R.id.bt_signin);
        btn.setOnClickListener(new mClick());
        signin = getSharedPreferences("singIn", Context.MODE_PRIVATE);
        edt01.setText(signin.getString("user",""));
        if(signin.getString("remember","").equals("1"))
        {
            cb01.setChecked(true);
            edt02.setText(signin.getString("password",""));
        }
        if(signin.getString("auto","").equals("1"))
        {
            cb02.setChecked(true);
        }
    }

    class mClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            String select = "_id = ";
            db = openOrCreateDatabase("HCZSInfo.db",MODE_PRIVATE,null);
            cursor = db.query("Users",null,"name=?",new String[]{edt01.getText().toString()},null,null,null);
            cursor.moveToNext();
            if(cursor.getCount()==0)
                Toast.makeText(getApplicationContext(),"该用户不存在，请先完成注册！",Toast.LENGTH_LONG);
            else if(!cursor.getString(4).equals(edt02.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"密码不正确，请重新登录！",Toast.LENGTH_LONG);
                edt02.setText("");
            }
            else
            {
                SharedPreferences.Editor editor = signin.edit();
                editor.putString("user",edt01.getText().toString());
                editor.putString("password",edt02.getText().toString());
                if(cb01.isChecked())
                    editor.putString("remember","1");
                else
                    editor.putString("remember","0");
                if(cb02.isChecked())
                    editor.putString("auto","1");
                else
                    editor.putString("auto","0");
                editor.commit();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
