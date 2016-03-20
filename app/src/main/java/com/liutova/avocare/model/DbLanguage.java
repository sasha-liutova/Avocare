package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("Language")
public class DbLanguage extends ParseObject {

    public static ParseQuery<DbLanguage> getQuery() {
        return ParseQuery.getQuery(DbLanguage.class);
    }

    public String getCode() {
        return getString("code");
    }

    public void setCode(String value) {
        this.put("code", value);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        this.put("name", value);
    }
}
