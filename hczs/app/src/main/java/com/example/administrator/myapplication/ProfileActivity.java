package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

public class ProfileActivity extends AppCompatActivity {

    private Button btn_pct,btn_n,btn_s,btn_a,btn_pw,btn_ph,btn_back,btn_tc;
    private TextView tv_n,tv_s,tv_a,tv_pw,tv_ph;
    private ImageView img;
    private static final int IMAGE = 1;
    String path;
    SQLiteDatabase db;
    SharedPreferences read;
    SharedPreferences.Editor editor;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tv_n=(TextView)findViewById(R.id.tv_n);
        tv_a=(TextView)findViewById(R.id.tv_a);
        tv_s=(TextView)findViewById(R.id.tv_s);
        tv_pw=(TextView)findViewById(R.id.tv_pw);
        tv_ph=(TextView)findViewById(R.id.tv_ph);
        img=(ImageView)findViewById(R.id.img_pct);
        btn_n=(Button)findViewById(R.id.btn_n);
        btn_n.setOnClickListener(new mClick());
        btn_s=(Button)findViewById(R.id.btn_sex);
        btn_s.setOnClickListener(new mClick());
        btn_a=(Button)findViewById(R.id.btn_age);
        btn_a.setOnClickListener(new mClick());
        btn_pw=(Button)findViewById(R.id.btn_pw);
        btn_pw.setOnClickListener(new mClick());
        btn_ph=(Button)findViewById(R.id.btn_ph);
        btn_ph.setOnClickListener(new mClick());
        btn_back=(Button)findViewById(R.id.back_main);
        btn_back.setOnClickListener(new mClick());
        btn_tc=(Button)findViewById(R.id.btn_tc);
        btn_tc.setOnClickListener(new mClick());
        btn_pct=(Button)findViewById(R.id.btn_pct);
        btn_pct.setOnClickListener(new mClick());
        read = getSharedPreferences("singIn", Context.MODE_PRIVATE);
        editor = read.edit();
        String username = read.getString("user","");
        db = openOrCreateDatabase("HCZSInfo.db",MODE_PRIVATE,null);
        cursor = db.query("Users",null,"name=?",new String[]{username},null,null,null);
        cursor.moveToNext();
        tv_n.setText(cursor.getString(1));
        tv_pw.setText(cursor.getString(4));
        if(cursor.getString(2) != null)
            tv_s.setText(cursor.getString(2));
        else
            tv_s.setText("未设置");
        if(cursor.getInt(3) != 0)
            tv_a.setText(cursor.getString(3));
        else
            tv_a.setText("未设置");
        if(cursor.getString(5) != null)
            tv_ph.setText(cursor.getString(5));
        else
            tv_ph.setText("未设置");
        if(cursor.getString(6) != null)
        {
            Bitmap bm = BitmapFactory.decodeFile(cursor.getString(6));
            img.setImageBitmap(bm);
        }
        else
            img.setBackgroundResource(R.drawable.yhtx);
    }

    public class mClick implements OnClickListener
    {
        @Override
        public void onClick(View view) {
            Intent intent_next = new Intent(ProfileActivity.this,EditActivity.class);
            if(view==btn_n)
            {
                editor.putString("do","编辑姓名");
                editor.commit();
                startActivity(intent_next);
            }
            else if(view==btn_s)
            {
                editor.putString("do","编辑性别");
                editor.commit();
                startActivity(intent_next);
            }
            else if(view==btn_a)
            {
                editor.putString("do","编辑年龄");
                editor.commit();
                startActivity(intent_next);
            }
            else if(view==btn_pw)
            {
                editor.putString("do","编辑密码");
                editor.commit();
                startActivity(intent_next);
            }
            else if(view==btn_ph)
            {
                editor.putString("do","编辑电话");
                editor.commit();
                startActivity(intent_next);
            }
            else if(view==btn_tc)
            {
                Intent intent = new Intent(ProfileActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
            else if(view==btn_pct)
            {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
                db.execSQL("update Users set picture=? where name=?",
                        new String[] {path,read.getString("user","")});
                db.close();
            }
            else
                finish();
                //startActivity(intent_back);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            path = imagePath;
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        img.setImageBitmap(bm);
    }
}
