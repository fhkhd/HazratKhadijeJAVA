package com.example.khadijejava;

import android.app.Application;

public class App extends Application {

    public static final String TAG = "qqq";

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized App getInstance() {return mInstance;}

}
