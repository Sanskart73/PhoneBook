package com.example.PhoneBook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sno;
    private String name;
    private String number;
    @ManyToOne
    private MainUser user;
    public Contact() {
    }
   
    public Contact(int sno, String name, String number, MainUser user) {
        this.sno = sno;
        this.name = name;
        this.number = number;
        this.user = user;
    }

    public int getSno() {
        return sno;
    }
    public void setSno(int sno) {
        this.sno = sno;
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
    public MainUser getUser() {
        return user;
    }
    public void setUser(MainUser user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Contact [sno=" + sno + ", name=" + name + ", number=" + number + ", user=" + user + "]";
    }

    
    
}
