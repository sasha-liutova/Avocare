package com.liutova.avocare.model;

import io.realm.RealmObject;

/**
 * Created by Oleksandra Liutova on 13-Mar-16.
 */
public class MbAlergens extends RealmObject {

    private String name;

    public MbAlergens() {
    }

    public MbAlergens(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
