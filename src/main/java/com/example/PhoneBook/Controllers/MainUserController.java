package com.example.PhoneBook.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.PhoneBook.Entity.Contact;
import com.example.PhoneBook.Entity.MainUser;
import com.example.PhoneBook.Repository.ContactRepo;
import com.example.PhoneBook.Repository.UserRepo;

@RequestMapping("/user")
@Controller
public class MainUserController 
{
    @Autowired
    UserRepo repo;
    
    @Autowired
    ContactRepo ContRepo;
       
    @PostMapping("/saveContact")
    public String saveContact(Contact contact)
    {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        MainUser user = repo.findMainUserByUsername(username);
        contact.setUser(user);

        List<Contact> contacts=user.getContacts();
        contacts.add(contact);
        
        user.setContacts(contacts);

        repo.save(user);
        return "redirect:/contactSave"; 
    
}
    @RequestMapping("/Addcontact")
     public String displayUser(Model model) 
    {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        MainUser user = repo.findMainUserByUsername(username);
        // char name = user.getName().charAt(0);
        // model.addAttribute("name",name);
        model.addAttribute("user",user);
        return "/user/AddContact.html";
    }

    @RequestMapping("/showContacts")
    public String showContact(Model model)
    {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        MainUser user = repo.findMainUserByUsername(username);
        List<Contact>contacts = user.getContacts();
        model.addAttribute("cont",contacts);
        MainUser temp= user;
        model.addAttribute("user",temp);
        return "/user/showContacts.html";
    }

    @RequestMapping("/remove/{sno}")
    public String removeContact(@PathVariable("sno")int sno)
    {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        MainUser user = repo.findMainUserByUsername(username);
        List<Contact>contacts = user.getContacts();
        ArrayList<Contact> temp=new ArrayList();
        for(Contact item : contacts)
        {
            if(item.getSno()!=sno)
            {
                temp.add(item);
            }
            
        }
        user.setContacts(temp);
        repo.save(user);
        return "redirect:/user/showContacts";
    }
    
    
    @GetMapping("/updateContact/{sno}")
    public String updateContact(@PathVariable("sno")int sno,Model model)
    {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        MainUser user = repo.findMainUserByUsername(username);
        String name=user.getName();
        Contact contact = ContRepo.findContactBySno(sno);
            model.addAttribute("name", name);

    model.addAttribute("data", contact);
    return "user/updateContact.html";

    }
    @PostMapping("/updateContact")
    public String updateContact(Contact temp)
    {

        ContRepo.save(temp);
        
     return "redirect:/user/showContacts";
    }
    @RequestMapping("/changePassword")
    public String changeUserPassword(Model model)
    {
     String username=SecurityContextHolder.getContext().getAuthentication().getName();
     MainUser user = repo.findMainUserByUsername(username);
     model.addAttribute("user",user);
     return "user/changeUserPassword.html";
    }
    
    @PostMapping("/savePassword")
    public String savePassword(MainUser temp)
    {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        MainUser user = repo.findMainUserByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(temp.getPassword(),user.getPassword()))
        {
            return "/user/sameUser.html";
        }
        else
        {
        user.setPassword(new BCryptPasswordEncoder().encode(temp.getPassword()));
        repo.save(user);
        return "redirect:/user/showContacts";
        }
       
    }
}
    