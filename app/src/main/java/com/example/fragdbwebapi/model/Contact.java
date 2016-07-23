package com.example.fragdbwebapi.model;

/**
 * Created by Promlert on 7/23/2016.
 */
public class Contact {

    public final String name;
    public final String phone;
    public final String picture;

    public Contact(String name, String phone, String picture) {
        this.name = name;
        this.phone = phone;
        this.picture = picture;
    }
}
