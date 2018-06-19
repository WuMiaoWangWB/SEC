package com.example.administrator.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase db;
    EditText edt1,edt2;
    Button bt_cx;
    ImageView btn;
    ListView lv;
    Cursor cursor;
    SharedPreferences read;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //允许开启访问本地文件权限
        requestWritePermission();
        edt1 = (EditText)findViewById(R.id.et_01);
        edt2 = (EditText)findViewById(R.id.et_02);
        btn = (ImageView) findViewById(R.id.profile);
        btn.setOnClickListener(new mClick());
        bt_cx = (Button)findViewById(R.id.bt_cx);
        bt_cx.setOnClickListener(new mClick());
        lv = (ListView)findViewById(R.id.lv);
        read = getSharedPreferences("singIn", Context.MODE_PRIVATE);
        String username = read.getString("user","");
        db = openOrCreateDatabase("HCZSInfo.db",MODE_PRIVATE,null);
        cursor = db.query("Users",null,"name=?",new String[]{username},null,null,null);
        cursor.moveToNext();
        if(cursor.getString(6) != null)
        {
            Bitmap bm = BitmapFactory.decodeFile(cursor.getString(6));
            btn.setImageBitmap(bm);
        }
        else
            btn.setBackgroundResource(R.drawable.yhtx);
    }

    private void requestWritePermission(){
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    public class mClick implements OnClickListener{
        public void onClick(View v){
            if(v == btn)
            {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
            else if(v == bt_cx)
            {
                int loca;
                Cursor cursor = db.query("Stops",null,"name=?",new String[]{edt2.getText().toString()},null,null,null);
                cursor.moveToNext();
                loca = Integer.parseInt(cursor.getString(1));
                Cursor cursor1 = db.query("Bus",null,"name=?",new String[]{edt1.getText().toString()},null,null,null);
                cursor1.moveToNext();
                String result[] = new String[6];
                int loop = 0;
                while(true)
                {
                    Cursor cursor2 = db.query("Stops",null,"_id=?",new String[]{cursor1.getString(2)},
                            null,null,null);
                    cursor2.moveToNext();
                    result[loop] = "车次："+cursor1.getString(1) +"  当前位置："+cursor2.getString(2)+"  距您几站："
                            +(Integer.parseInt(cursor1.getString(2))>loca?(Integer.parseInt(cursor1.getString(2))-loca):loca-Integer.parseInt(cursor1.getString(2)))
                            +"站  方向："+cursor1.getString(3);
                    if(Integer.parseInt(cursor1.getString(2))>loca && cursor1.getString(3).equals("反"))
                        result[loop] = result[loop]+"    预计到达时间："+(Integer.parseInt(cursor1.getString(2))-loca)*3+"分钟";
                    else if(Integer.parseInt(cursor1.getString(2))<loca && cursor1.getString(3).equals("顺"))
                        result[loop] = result[loop]+"    预计到达时间："+(loca-Integer.parseInt(cursor1.getString(2)))*3+"分钟";
                    else
                        result[loop] = result[loop]+"    这一班车已过站";
                    if(cursor1.isLast())
                        break;
                    cursor1.moveToNext();
                    loop++;
                }
                lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,result));
            }
        }
    }
}
