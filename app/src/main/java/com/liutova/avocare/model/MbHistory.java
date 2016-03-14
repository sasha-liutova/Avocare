package com.liutova.avocare.model;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Oleksandra Liutova on 13-Mar-16.
 */
public class MbHistory extends RealmObject {

    private String productID;
    private Date date;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
