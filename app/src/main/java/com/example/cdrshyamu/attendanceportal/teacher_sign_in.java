package com.example.cdrshyamu.attendanceportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class teacher_sign_in extends AppCompatActivity {
    private EditText eml;
    private EditText pas;
    private Button  signin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_in);
        eml=(EditText)findViewById(R.id.teacheremail);
        pas=(EditText)findViewById(R.id.teacherpass);
        signin=(Button)findViewById(R.id.teacher_signin);
        mAuth=FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignin();
            }
        });

    }

    private void startSignin() {
        String email=eml.getText().toString().trim();
        String pass=pas.getText().toString().trim();
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass))
        {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(teacher_sign_in.this,"Sign In Successfully",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(teacher_sign_in.this,teacher_prof.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(teacher_sign_in.this,"Invalid email or password0",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else
        {
            Toast.makeText(teacher_sign_in.this,"Email & password cannot be blank",Toast.LENGTH_SHORT).show();
        }
    }
}
