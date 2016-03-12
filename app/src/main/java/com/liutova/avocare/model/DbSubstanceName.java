package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("SubstanceName")
public class DbSubstanceName extends ParseObject {

    public String getSubstanceID() {
        return getString("substanceID");
    }

    public void setSubstanceID(String value) {
        this.put("substanceID", value);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        this.put("name", value);
    }

    public String getLanguageID() {
        return getString("languageID");
    }

    public void setLanguageID(String value) {
        this.put("languageID", value);
    }

    public static ParseQuery<DbSubstance> getQuery() {
        return ParseQuery.getQuery(DbSubstance.class);
    }
}
