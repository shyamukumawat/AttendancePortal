package com.example.cdrshyamu.attendanceportal;

import android.graphics.Bitmap;

public class Student {

    public String s_name;
    public String s_rollno;
    public String s_semester;
    public String s_courseName;
    public String s_courseId;

    //constructor


    public Student(String n,
                   String r,
                   String cn,
                   String s,
                   String cid){
        s_name = n;
        s_rollno = r;
        s_semester = s;
        s_courseName = cn;
        s_courseId = cid;
    }


}
