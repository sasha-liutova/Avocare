package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("SafetyLevel")
public class DbSafetyLevel extends ParseObject {

    public String getLevel() {
        return getString("level");
    }

    public void setLevel(String value) {
        this.put("level", value);
    }


    public static ParseQuery<DbSubstance> getQuery() {
        return ParseQuery.getQuery(DbSubstance.class);
    }
}
