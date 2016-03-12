package com.liutova.avocare;

import android.app.Application;

import com.liutova.avocare.model.DbSubstance;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
public class AvocareApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(DbSubstance.class);
        Parse.initialize(this,"JJ2195iNCXVXOsooWXq67WEcJFafwY21dMwBWqtQ","mwJEKkjy5942EATsqHjrTa9e3Rh6pouLDmgkEyLA");
    }
}
