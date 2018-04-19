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

public class Te_register extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private Button   btnreg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_te_register);
        email=(EditText)findViewById(R.id.Tregemail);
        pass =(EditText)findViewById(R.id.Tregpass);
        btnreg=(Button)findViewById(R.id.Tregister);
        mAuth=FirebaseAuth.getInstance();

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });
    }

    private void startRegister() {
        String eml=email.getText().toString().trim();
        String pas=pass.getText().toString().trim();
        if(!TextUtils.isEmpty(eml)&&!TextUtils.isEmpty(pas))
        {
            mAuth.createUserWithEmailAndPassword(eml,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Intent i=new Intent(Te_register.this,teacher_register.class);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(Te_register.this,"Try Again",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        else
        {
            Toast.makeText(Te_register.this,"Try Again",Toast.LENGTH_SHORT).show();
        }
    }
}



