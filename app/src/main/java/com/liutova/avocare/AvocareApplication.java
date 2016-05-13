package com.liutova.avocare;

import android.app.Application;

import com.liutova.avocare.helper.Helper;
import com.liutova.avocare.model.DbError;
import com.liutova.avocare.model.DbLanguage;
import com.liutova.avocare.model.DbProduct;
import com.liutova.avocare.model.DbProductBarcode;
import com.liutova.avocare.model.DbProductDescription;
import com.liutova.avocare.model.DbProductsToSubstances;
import com.liutova.avocare.model.DbSafetyLevel;
import com.liutova.avocare.model.DbSafetyLevelDescription;
import com.liutova.avocare.model.DbSubstance;
import com.liutova.avocare.model.DbSubstanceDescription;
import com.liutova.avocare.model.DbSubstanceName;
import com.liutova.avocare.model.DbSubstanceRequest;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
public class AvocareApplication extends Application {

    private static AvocareApplication sApp;

    public static AvocareApplication getAppContext() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;

        String languageCode = getSharedPreferences("preferences", MODE_PRIVATE).getString("languageCode", null);
        if(languageCode != null){
            getResources().updateConfiguration(Helper.getConfigurationFromPreferences(languageCode), getResources().getDisplayMetrics());
        }

        ParseObject.registerSubclass(DbLanguage.class);
        ParseObject.registerSubclass(DbProduct.class);
        ParseObject.registerSubclass(DbProductBarcode.class);
        ParseObject.registerSubclass(DbProductDescription.class);
        ParseObject.registerSubclass(DbProductsToSubstances.class);
        ParseObject.registerSubclass(DbSafetyLevel.class);
        ParseObject.registerSubclass(DbSafetyLevelDescription.class);
        ParseObject.registerSubclass(DbSubstance.class);
        ParseObject.registerSubclass(DbSubstanceDescription.class);
        ParseObject.registerSubclass(DbSubstanceName.class);
        ParseObject.registerSubclass(DbSubstanceRequest.class);
        ParseObject.registerSubclass(DbError.class);
        Parse.initialize(this,"JJ2195iNCXVXOsooWXq67WEcJFafwY21dMwBWqtQ","mwJEKkjy5942EATsqHjrTa9e3Rh6pouLDmgkEyLA");
    }
}
