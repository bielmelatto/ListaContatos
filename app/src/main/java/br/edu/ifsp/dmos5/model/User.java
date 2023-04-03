package br.edu.ifsp.dmos5.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Contact> contacts;

    public User(String username, String password){
        this.contacts = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Contact> getContacts(){
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }
}
