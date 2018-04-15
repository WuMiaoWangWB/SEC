package com.example.administrator.myapplication;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit);
        btn1=(Button)findViewById(R.id.clean01);
        btn2=(Button)findViewById(R.id.clean02);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_back=(Button)findViewById(R.id.back_profile);
        btn1.setOnClickListener(new mClick());
        btn2.setOnClickListener(new mClick());
        btn_sure.setOnClickListener(new mClick());
        btn_back.setOnClickListener(new mClick());
    }

    public class mClick implements OnClickListener
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(EditActivity.this,ProfileActivity.class);
            Bundle bundle = new Bundle();
            String str = getResources().getString(R.string.editTitle);
            if(view==btn1)
                edit1.setText("");
            else if(view==btn2)
                edit2.setText("");
            else if(view==btn_sure)
            {
                if(!str.equals("编辑密码"))
                {
                    bundle.putString("text", edit1.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(edit1.getText().equals(edit2.getText()))
                {
                    bundle.putString("text", edit1.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"两次输入的密码不同!",Toast.LENGTH_SHORT).show();
                    edit1.setText("");
                    edit2.setText("");
                }
            }
            else
                startActivity(intent);
        }
    }
}
