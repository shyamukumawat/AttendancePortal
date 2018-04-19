package com.example.cdrshyamu.attendanceportal;

import android.content.Intent;
import android.media.MediaCodec;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class teacher_register extends AppCompatActivity {
   private EditText Tname,Tcoursename,Tcourseid,Tmob;
   private DatabaseReference teacherDatabase;
   private Button BtnTregister;
   private Firebase tref;
   private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        Tname=(EditText)findViewById(R.id.name);
        Tcoursename=(EditText)findViewById(R.id.coursename);
        Tcourseid=(EditText)findViewById(R.id.courseid);
        Tmob=(EditText)findViewById(R.id.mobile);
        BtnTregister=(Button)findViewById(R.id.Tregister);
        tref=new Firebase("https://android-73c7c.firebaseio.com/Teachers");
        mAuth=FirebaseAuth.getInstance();
        BtnTregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teacherName=null;
                String teacherCourseName=null;
                String teacherCourseId=null;
                String teacherMob=null;
                int flag=0;
                if(Tname.getText().toString().length()==0)
                {  flag=1;
                    Tname.setError("Please Enter Name");
                    Tname.requestFocus();
                }
                else
                {
                    teacherName = Tname.getText().toString().toUpperCase();
                }
                if(Tcoursename.getText().toString().length()==0)
                {   flag=1;
                    Tcoursename.setError("Enter Course Name");
                    Tcoursename.requestFocus();
                }
                else
                {

                    teacherCourseName=Tcoursename.getText().toString();
                }

                if(Tcourseid.getText().toString().length()<5&&
                        !isValidCourseid(Tcourseid.getText().toString().toUpperCase()))
                {  flag=1;
                    Tcourseid.setError("Enter Valid CurseId");
                    Tcourseid.requestFocus();
                }
                else
                {

                    teacherCourseId=Tcourseid.getText().toString().toUpperCase();
                }

                if(Tmob.getText().toString().length()!=10&&
                        !isValidMob(Tmob.getText().toString()))
                {    flag=1;
                    Tmob.setError("Enter Valid Mobile NO");
                }
                else
                {

                    teacherMob=Tmob.getText().toString();
                }
                if(flag==1)
                {
                    Toast.makeText(teacher_register.this,"Invalid Input",Toast.LENGTH_LONG).show();

                }
                else
                {    String user_id=mAuth.getCurrentUser().getUid();
                    Firebase tchild=tref.child(user_id);

                    Teacher teacherObject= new Teacher(teacherName,  teacherCourseName,
                            teacherCourseId, teacherMob);
                    tchild.setValue(teacherObject);

                    Toast.makeText(teacher_register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(teacher_register.this,teacher_login.class);
                    startActivity(i);
                }



            }
        });
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static boolean isValidEmail(final String Email)
    {  Pattern pattern;
       Matcher matcher;
       final String EMAIL_PATTERN="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
       pattern=Pattern.compile(EMAIL_PATTERN);
       matcher=pattern.matcher(Email);
       return  matcher.matches();

    }
    public static boolean isValidCourseid(final String courseid)
    {  Pattern pattern;
       Matcher matcher;
       final String CORSE_PATTERN="A-Z{2}0-9{3}";
        pattern=Pattern.compile(CORSE_PATTERN);
        matcher=pattern.matcher(courseid);
        return  matcher.matches();

    }
    public static boolean isValidMob(final String mob)
    {   Pattern pattern;
        Matcher matcher;
        final String MOB_PATTERN="[0-9]{10}";
        pattern=Pattern.compile(MOB_PATTERN);
        matcher=pattern.matcher(mob);
        return  matcher.matches();

    }


}
