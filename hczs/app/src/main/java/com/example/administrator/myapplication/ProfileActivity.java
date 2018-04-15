package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private Button btn_pct,btn_n,btn_s,btn_a,btn_pw,btn_ph,btn_back,btn_tc;
    private TextView tv_n,tv_s,tv_a,tv_pw,tv_ph;
    private ImageView img;
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
    }

    public class mClick implements OnClickListener
    {
        @Override
        public void onClick(View view) {

        }
    }
}
