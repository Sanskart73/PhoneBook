package com.example.PhoneBook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PhoneBook.Entity.MainUser;

public interface UserRepo extends JpaRepository<MainUser,Integer> {
    
    public MainUser findMainUserByUsername(String username); 
}
