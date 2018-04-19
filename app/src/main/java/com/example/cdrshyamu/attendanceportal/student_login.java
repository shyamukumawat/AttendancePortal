package com.example.cdrshyamu.attendanceportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class student_login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

    }
    public void Action1 (View v)
    {



        Intent i=new Intent(student_login.this,St_Register.class);
        startActivity(i);
    }
    public void Action2  (View v)
    {

        Intent j=new Intent(student_login.this,student_sign_in.class);
        startActivity(j);
    }









}
