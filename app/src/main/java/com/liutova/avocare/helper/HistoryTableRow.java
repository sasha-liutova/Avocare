package com.liutova.avocare.helper;

/**
 * Created by Oleksandra Liutova on 21-Apr-16.
 */
public class HistoryTableRow {
    String date;
    String name;
    String id;

    public HistoryTableRow(String date, String name, String id) {
        this.date = date;
        this.name = name;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
