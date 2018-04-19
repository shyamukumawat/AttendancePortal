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

public class St_Register extends AppCompatActivity {
    private EditText student_supemail,student_suppass;
    private Button   student_signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st__register);
        student_supemail=(EditText)findViewById(R.id.stsignupemail);
        student_suppass=(EditText)findViewById(R.id.stsignuppassword);
        student_signup=(Button)findViewById(R.id.stSignup);
        mAuth=FirebaseAuth.getInstance();

        student_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startRegister();


            }
        });

    }

    private void startRegister() {
        String email=student_supemail.getText().toString().trim();
        String pass=student_signup.getText().toString();
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass))
        {
            if(student_register.isValidEmail(email)){
                if(pass.length()>6)
                {mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent i=new Intent(St_Register.this,student_register.class);
                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(St_Register.this,"Try Again",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                }
                else
                {
                    Toast.makeText(St_Register.this,"Password must have at least one spcl char, one caps letter and digits with minimium size 8",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(St_Register.this,"Invalid Email",Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(St_Register.this,"Try Again",Toast.LENGTH_SHORT).show();
        }
    }
}
