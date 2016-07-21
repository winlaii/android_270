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
        ParseObject.registerSubclass(Drink.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("76ee57f8e5f8bd628cc9586e93d428d5")
                        // .applicationId("IR23y6bF8llIud60QcedLNCBQjpjATSM9h9f3tMG")
                .server("http://parseserver-ps662-env.us-east-1.elasticbeanstalk.com/parse/")
                        // .server("https://parseapi.back4app.com/")
                        // .clientKey("8kQhQafqI5jIG2ASpcZYBvk9Lh5QPEDkIMs6vCSD")
                .enableLocalDataStore()
                .build());
    }
}
