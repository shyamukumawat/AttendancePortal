package com.example.cdrshyamu.attendanceportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class teacher_prof extends AppCompatActivity {
    private TextView name;
    private TextView Course;
    private TextView id;
    private TextView date;
    private FirebaseAuth mAuth;
    private Firebase db;
    private Button sout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_prof);
        name=(TextView)findViewById(R.id.Tpname);
        Course=(TextView)findViewById(R.id.Tpcousrename);
        id=(TextView)findViewById(R.id.Tpcourseid);
        sout=(Button)findViewById(R.id.signOut);
        mAuth=FirebaseAuth.getInstance();
        db=new Firebase("https://android-73c7c.firebaseio.com/Teachers/");
        showprofile();

        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i=new Intent(teacher_prof.this,Home.class);
                startActivity(i);
            }
        });

    }

    private void showprofile() {
        final String userid;
        userid=mAuth.getCurrentUser().getUid();
          final Firebase data=db.child(userid);
          data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String,String> map=dataSnapshot.getValue(Map.class);
                        String n=map.get("teaName");
                        String cn=map.get("teacourseName");
                        String cid=map.get("teacourseId");
                        name.setText(n);
                        Course.setText(cn);
                        id.setText(cid);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
