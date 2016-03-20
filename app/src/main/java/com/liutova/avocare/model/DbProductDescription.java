package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("ProductDescription")
public class DbProductDescription extends ParseObject{

    public static ParseQuery<DbProductDescription> getQuery() {
        return ParseQuery.getQuery(DbProductDescription.class);
    }

    public String getProductID() {
        return getString("productID");
    }

    public void setProductID(String value) {
        this.put("productID", value);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        this.put("name", value);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String value) {
        this.put("description", value);
    }

    public String getLanguageID() {
        return getString("languageID");
    }

    public void setLanguageID(String value) {
        this.put("languageID", value);
    }

}
