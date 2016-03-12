package com.liutova.avocare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
@ParseClassName("ProductBarcode")
public class DbProductBarcode extends ParseObject {

    public String getProductID() {
        return getString("productID");
    }

    public void setProductID(String value) {
        this.put("productID", value);
    }

    public String getBarcode() {
        return getString("barcode");
    }

    public void setBarcode(String value) {
        this.put("barcode", value);
    }

    public String getCountry() {
        return getString("country");
    }

    public void setCountry(String value) {
        this.put("country", value);
    }


    public static ParseQuery<DbSubstance> getQuery() {
        return ParseQuery.getQuery(DbSubstance.class);
    }
}
