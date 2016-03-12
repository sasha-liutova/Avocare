package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("Substance")
public class DbSubstance extends ParseObject {

    public String getSafetyLevelID() {
        return getString("safetyLevelID");
    }

    public void setSafetyLevelID(String value) {
        this.put("safetyLevelID", value);
    }


    public static ParseQuery<DbSubstance> getQuery() {
        return ParseQuery.getQuery(DbSubstance.class);
    }
}
