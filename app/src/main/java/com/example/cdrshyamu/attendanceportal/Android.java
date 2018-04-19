package com.example.cdrshyamu.attendanceportal;

import android.app.Application;

import com.firebase.client.Firebase;

public class Android extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
