package com.example.cdrshyamu.attendanceportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class teacher_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
    }
    public void Action1(View v)
    {
        Intent i=new Intent(teacher_login.this,Te_register.class);
        startActivity(i);

    }
    public void Action2(View v)
    {
        Intent j=new Intent(teacher_login.this,teacher_sign_in.class);
        startActivity(j);
    }
}
