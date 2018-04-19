package com.example.cdrshyamu.attendanceportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void Action1(View v)
    {
        Intent i=new Intent(Home.this,teacher_login.class);
        startActivity(i);

    }
    public void Action2(View v)
    {
        Intent j=new Intent(Home.this,student_login.class);
        startActivity(j);
    }
}
