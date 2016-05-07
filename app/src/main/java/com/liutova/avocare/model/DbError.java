package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 22-Apr-16.
 */
@ParseClassName("Error")
public class DbError extends ParseObject {

    public static ParseQuery<DbError> getQuery() {
        return ParseQuery.getQuery(DbError.class);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setStatus(String value) {
        this.put("status", value);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String value) {
        if (value != null) {
            this.put("description", value);
        } else {
            this.put("description", "");
        }
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String value) {
        this.put("type", value);
    }

    public String getProductID() {
        return getString("productID");
    }

    public void setProductID(String value) {
        this.put("productID", value);
    }

    public String getLanguageID() {
        return getString("languageID");
    }

    public void setLanguageID(String value) {
        this.put("languageID", value);
    }
}
