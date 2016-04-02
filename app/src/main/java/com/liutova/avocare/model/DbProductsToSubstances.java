package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("ProductsToSubstances")
public class DbProductsToSubstances extends ParseObject{

    public static ParseQuery<DbProductsToSubstances> getQuery() {
        return ParseQuery.getQuery(DbProductsToSubstances.class);
    }

    public String getProductID() {
        return getString("productID");
    }

    public void setProductID(String value) {
        this.put("productID", value);
    }

    public String getSubstanceID() {
        return getString("substanceID");
    }

    public void setSubstanceID(String value) {
        this.put("substanceID", value);
    }

    public int getOrderInComposition() {
        return getInt("orderInComposition");
    }

    public void setOrderInComposition(int value) {
        this.put("orderInComposition", value);
    }
}
