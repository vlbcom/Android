package com.vlbcom.contacts;

import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private ArrayList<Contact> contacts;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.contacts= new ArrayList<>();
    }


    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
