package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("SafetyLevel")
public class DbSafetyLevel extends ParseObject {

    public static ParseQuery<DbSafetyLevel> getQuery() {
        return ParseQuery.getQuery(DbSafetyLevel.class);
    }

    public int getLevel() {
        return getInt("level");
    }

    public void setLevel(int value) {
        this.put("level", value);
    }
}
