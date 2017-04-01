package com.company;

/**
 * Created by Ramzan Dieze on 01/07/2016.
 */

public class Rooms {
    //private variables for customer in the room
    private String firstName;
    private String surName;
    private String id;

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String aName) {
        firstName = aName;
    }

    public String getName() {
        return firstName;
    }
}
