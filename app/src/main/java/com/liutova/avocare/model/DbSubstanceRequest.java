package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("SubstanceRequest")
public class DbSubstanceRequest extends ParseObject {

    public static ParseQuery<DbSubstance> getQuery() {
        return ParseQuery.getQuery(DbSubstance.class);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        this.put("name", value);
    }

    public int getCounter() {
        return getInt("counter");
    }

    public void setCounter(int value) {
        this.put("counter", value);
    }
}
