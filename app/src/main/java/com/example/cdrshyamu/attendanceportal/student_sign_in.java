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

public class student_sign_in extends AppCompatActivity {
     private EditText st_eml;
     private EditText st_pass;
     private Button   login;
     private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);
        st_eml=(EditText)findViewById(R.id.studenteml);
        st_pass=(EditText)findViewById(R.id.studenpass);
        login=(Button)findViewById(R.id.student_login);
        mAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });
    }

    private void startLogin() {
          String eml=st_eml.getText().toString().trim();
          String pass=st_pass.getText().toString().trim();
      if(!TextUtils.isEmpty(eml)&&!TextUtils.isEmpty(pass))
      {
          mAuth.signInWithEmailAndPassword(eml,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful())
                  {   Toast.makeText(student_sign_in.this,"Logged In successfully",Toast.LENGTH_LONG).show();
                      Intent i=new Intent(student_sign_in.this,student_prof.class);
                      startActivity(i);
                  }
                  else
                  {
                      Toast.makeText(student_sign_in.this,"Login Failed",Toast.LENGTH_LONG).show();
                  }
              }
          });
      }
      else
      {
          Toast.makeText(student_sign_in.this,"Emal & password cannot be blank",Toast.LENGTH_SHORT).show();
      }
    }

}
