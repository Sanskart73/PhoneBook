package com.example.PhoneBook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PhoneBook.Entity.Contact;

public interface  ContactRepo extends JpaRepository<Contact, Integer> {
    public Contact findContactByNumber(String number);
    public Contact findContactBySno(int sno);
}
