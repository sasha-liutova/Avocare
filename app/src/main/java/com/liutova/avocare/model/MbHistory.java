package com.liutova.avocare.model;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Oleksandra Liutova on 13-Mar-16.
 */
public class MbHistory extends RealmObject {

    private String productName;
    private Date date;
    private String productID;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
