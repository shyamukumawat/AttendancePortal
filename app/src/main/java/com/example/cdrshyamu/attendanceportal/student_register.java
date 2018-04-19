package com.example.cdrshyamu.attendanceportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class student_register extends AppCompatActivity {
    private Button streg;
    private ImageView selectedImage;
    private final static int Gallery_Intent=2;
    private ProgressDialog myProgressdialog;
    private StorageReference  Mstrorage;
    private Firebase mref;
    private FirebaseAuth mAuth;
    private int flg=0;



    EditText name,eml,sem,cname,cid,paswrd,roll;
    Button Btnregister,Btncamera;
    ImageView selectImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

             Btncamera =(Button)findViewById(R.id.btnncamera);
             name=(EditText)findViewById(R.id.stname);
             eml=(EditText)findViewById(R.id.stemail);
             roll=(EditText)findViewById(R.id.strollno);
             sem=(EditText)findViewById(R.id.stsem);
             cname=(EditText)findViewById(R.id.stcoursename);
             cid=(EditText)findViewById(R.id.stcourseid);
             paswrd=(EditText)findViewById(R.id.password);
             Btnregister = (Button) findViewById(R.id.stregister);
             selectImage=(ImageView)findViewById(R.id.selectedImage);
             myProgressdialog=new ProgressDialog(this);
             mAuth= FirebaseAuth.getInstance();
             mref=new Firebase("https://android-73c7c.firebaseio.com/Students");


        Btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,Gallery_Intent);
            }

        });



        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String student_name  = null;
                String student_roll  = null;
                String student_cname = null;
                String student_cid   = null;
                String student_sem   = null;
                int flag=0;
                if(name.getText().toString().length()==0)
                {  flag=1;
                    name.setError("Name Cannot be Blank");
                    name.requestFocus();

                }
                else
                {
                    student_name=name.getText().toString().toUpperCase();
                }

                if(roll.getText().toString().length()!=8)
                {  flag=1;
                    roll.setError("Roll no is not valid");
                    roll.requestFocus();
                }
                else
                {
                   student_roll=roll.getText().toString().toUpperCase();
                }



                if(cname.getText().toString().length()==0)
                {   flag=1;
                    cname.setError("Course Name cannot be blank");
                    cname.requestFocus();
                }
                else
                {
                    student_cname=cname.getText().toString();
                }
                if(cid.getText().toString().length()!=5&&!isValidCourseid(cid.getText().toString().toUpperCase()))
                {   flag=1;
                    cid.setError("Enter valid course id i.e. CS313");
                    cid.requestFocus();
                }
                else
                {
                    student_cid=cid.getText().toString().toUpperCase();
                }
                if(sem.getText().toString().length()!=1)
                {   flag=1;
                    sem.setError("Enter semester");
                    sem.requestFocus();

                }
                else
                {
                    student_sem=sem.getText().toString();
                }
                if(flag==1)
                {
                    Toast.makeText(student_register.this,"Invalid Input",Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(flg==1)
                    {
                        String user_id=mAuth.getCurrentUser().getUid();
                        Firebase childref=mref.child(user_id);
                        Student stdObject = new Student(student_name,student_roll,
                                student_cname, student_sem,student_cid);
                        childref.setValue(stdObject);

                        Toast.makeText(student_register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(student_register.this, Home.class);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(student_register.this,"Photo Updation not done",Toast.LENGTH_LONG).show();
                    }







                }

            }
        });








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent ReturnedImagedata) {
        super.onActivityResult(requestCode, resultCode, ReturnedImagedata);
        //Bitmap bitmap=(Bitmap)data.getExtras().get("data");
                if (requestCode==Gallery_Intent && resultCode==RESULT_OK)
                {
                   Uri Image=ReturnedImagedata.getData();
                   Log.i("Student_register","Selected Image"+selectImage);

                   this.selectImage.setImageURI(Image);
                   String user_id=mAuth.getCurrentUser().getUid();
                   Mstrorage= FirebaseStorage.getInstance().getReference().child(user_id);
                   StorageReference filepath= Mstrorage.child("photo" +
                           "").child(Image.getLastPathSegment());
                   myProgressdialog.setMessage("Uploading..");
                   myProgressdialog.show();
                   Mstrorage.putFile(Image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           myProgressdialog.dismiss();
                           flg=1;
                           Toast.makeText(student_register.this,"Upload Done",Toast.LENGTH_LONG).show();
                       }
                   });


                  // this.uploadImagetoFirebase();

                }



    }

      private void uploadImagetoFirebase()
      {  this.selectImage.setDrawingCacheEnabled(true);
         this.selectImage.buildDrawingCache();
         Bitmap bitmap=this.selectImage.getDrawingCache();
         ByteArrayOutputStream boos=new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG,1080,boos);
         byte[] data1=boos.toByteArray();
      }


    public static boolean isValidEmail(final String Email)
    {  Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        pattern=Pattern.compile(EMAIL_PATTERN);
        matcher=pattern.matcher(Email);
        return  matcher.matches();

    }
    public static boolean isValidCourseid(final String cd)
    {  Pattern pattern;
        Matcher matcher;
        final String CORSE_PATTERN="A-Z{2}0-9{3}";
        pattern=Pattern.compile(CORSE_PATTERN);
        matcher=pattern.matcher(cd);
        return  matcher.matches();

    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
