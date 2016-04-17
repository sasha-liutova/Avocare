package com.liutova.avocare.model;

import io.realm.RealmObject;

/**
 * Created by Oleksandra Liutova on 13-Mar-16.
 */
public class MbFavourites extends RealmObject {

    private String productID;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
