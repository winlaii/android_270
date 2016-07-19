package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by user on 2016/7/19.
 */
public class SimpleUIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IR23y6bF8llIud60QcedLNCBQjpjATSM9h9f3tMG")
                .server("https://parseapi.back4app.com/")
                .clientKey("8kQhQafqI5jIG2ASpcZYBvk9Lh5QPEDkIMs6vCSD")
                .build());
    }
}
