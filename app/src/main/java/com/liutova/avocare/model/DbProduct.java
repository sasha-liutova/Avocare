package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("Product")
public class DbProduct extends ParseObject {

    public String getSafetyLevelID() {
        return getString("safetyLevelID");
    }

    public void setSafetyLevelID(String value) {
        this.put("safetyLevelID", value);
    }

    public ParseFile getPhoto() {
        return getParseFile("photo");
    }

    public void setPhoto(ParseFile value) {
        this.put("photo", value);
    }

    public static ParseQuery<DbSubstance> getQuery() {
        return ParseQuery.getQuery(DbSubstance.class);
    }
}
