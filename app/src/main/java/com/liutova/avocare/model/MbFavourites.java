package com.liutova.avocare.model;

import io.realm.RealmObject;

/**
 * Created by Oleksandra Liutova on 13-Mar-16.
 */
public class MbFavourites extends RealmObject {

    private String productID;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
