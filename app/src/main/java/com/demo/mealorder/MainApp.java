package com.demo.mealorder;

import android.app.Application;

import com.idescout.sql.SqlScoutServer;

public class MainApp extends Application {
    public static MainApp singleInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        singleInstance = this;
        SqlScoutServer.create(this, getPackageName());
    }

    public static MainApp getInstance() {
        return singleInstance;
    }
}
