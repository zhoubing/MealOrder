package com.demo.mealorder;

import android.app.Application;

public class MainApp extends Application {
    public static MainApp singleInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        singleInstance = this;
    }

    public static MainApp getInstance() {
        return singleInstance;
    }
}
