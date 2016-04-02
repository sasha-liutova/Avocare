package com.liutova.avocare.helper;

/**
 * Created by Oleksandra Liutova on 02-Apr-16.
 */
public class CompositionTableRow {
    private int index;
    private String id;
    private String name;
    private String safetyLevelID;
    private int safetyLevel;
    private String safetyLevelDescription;
    private String description;

    public CompositionTableRow(int index, String id) {
        this.index = index;
        this.id = id;
    }

    public String getSafetyLevelDescription() {
        return safetyLevelDescription;
    }

    public void setSafetyLevelDescription(String safetyLevelDescription) {
        this.safetyLevelDescription = safetyLevelDescription;
    }

    public int getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSafetyLevelID() {
        return safetyLevelID;
    }

    public void setSafetyLevelID(String safetyLevelID) {
        this.safetyLevelID = safetyLevelID;
    }

    public int getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(int safetyLevel) {
        this.safetyLevel = safetyLevel;
    }
}
