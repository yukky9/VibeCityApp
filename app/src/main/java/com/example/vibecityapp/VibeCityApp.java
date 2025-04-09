package com.example.vibecityapp;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class VibeCityApp extends Application {
    private static VibeCityApp instance;
    private String authToken;

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("a44af384-c275-4d80-8b42-909f5d87077b");
        instance = this;
    }

    public static VibeCityApp getInstance() {
        return instance;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}