package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by user on 2016/7/19.
 */
public class SimpleUIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Order.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IR23y6bF8llIud60QcedLNCBQjpjATSM9h9f3tMG")
                .server("https://parseapi.back4app.com/")
                .clientKey("8kQhQafqI5jIG2ASpcZYBvk9Lh5QPEDkIMs6vCSD")
                .enableLocalDataStore()
                .build());
    }
}
