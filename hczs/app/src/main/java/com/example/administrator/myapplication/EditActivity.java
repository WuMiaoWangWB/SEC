package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity
{
    private Button btn1,btn2,btn_sure,btn_back;
    private EditText edit1,edit2;
    SharedPreferences read;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit);
        edit1 = (EditText)findViewById(R.id.editSet);
        edit2 = (EditText)findViewById(R.id.editSure);
        btn1=(Button)findViewById(R.id.clean01);
        btn2=(Button)findViewById(R.id.clean02);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_back=(Button)findViewById(R.id.back_profile);
        btn1.setOnClickListener(new mClick());
        btn2.setOnClickListener(new mClick());
        btn_sure.setOnClickListener(new mClick());
        btn_back.setOnClickListener(new mClick());
        read = getSharedPreferences("singIn",MODE_PRIVATE);
        db = openOrCreateDatabase("HCZSInfo.db",MODE_PRIVATE,null);
        if(read.getString("do","").equals("编辑密码"))
        {
            edit2.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
        }
    }

    public class mClick implements OnClickListener
    {
        @Override
        public void onClick(View view) {
            String str = "";
            if(view==btn1)
                edit1.setText("");
            else if(view==btn2)
                edit2.setText("");
            else if(view==btn_sure)
            {
                if(read.getString("do","").equals("编辑密码"))
                {
                    if(edit1.getText().toString().equals(edit2.getText().toString()))
                    {
                        db.execSQL("update Users set password=? where name=?",
                                new String[] {edit2.getText().toString(),read.getString("user","")});
                        db.close();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"两次输入的密码不同!",Toast.LENGTH_SHORT).show();
                        edit1.setText("");
                        edit2.setText("");
                    }
                }
                else if(read.getString("do","").equals("编辑姓名"))
                {
                    db.execSQL("update Users set name=? where name=?",
                            new String[] {edit1.getText().toString(),read.getString("user","")});
                    db.close();
                    finish();
                }
                else if(read.getString("do","").equals("编辑性别"))
                {
                    db.execSQL("update Users set sex=? where name=?",
                            new String[] {edit1.getText().toString(),read.getString("user","")});
                    db.close();
                    finish();
                }
                else if(read.getString("do","").equals("编辑年龄"))
                {
                    db.execSQL("update Users set age=? where name=?",
                            new String[] {edit1.getText().toString(),read.getString("user","")});
                    db.close();
                    finish();
                }
                else if(read.getString("do","").equals("编辑电话"))
                {
                    db.execSQL("update Users set phone=? where name=?",
                            new String[] {edit1.getText().toString(),read.getString("user","")});
                    db.close();
                    finish();
                }
            }
            else
                finish();
        }
    }
}
