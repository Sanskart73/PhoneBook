package com.example.PhoneBook.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MainUser implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cid;
    private String name;
    private String number;
    private String type,username,password;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Contact> contacts;
    public MainUser() {
    }
   
    public MainUser(int cid, String name, String number, String type, String username, String password,
            List<Contact> contacts) {
        this.cid = cid;
        this.name = name;
        this.number = number;
        this.type = type;
        this.username = username;
        this.password = password;
        this.contacts = contacts;
    }

    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
   
    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     ArrayList<SimpleGrantedAuthority> data = new ArrayList<>();    
        data.add(new SimpleGrantedAuthority("User"));
        return data;    
    }

}
