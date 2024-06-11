package com.example.firstapp;

import android.app.Application;

import com.example.firstapp.db.DBManager;

//表示全局应用的类
public class UnitApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initDB(getApplicationContext());
    }

}
