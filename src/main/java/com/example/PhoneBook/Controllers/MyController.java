package com.example.PhoneBook.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PhoneBook.Entity.Contact;
import com.example.PhoneBook.Entity.MainUser;
import com.example.PhoneBook.Repository.ContactRepo;
import com.example.PhoneBook.Repository.UserRepo;

@Controller
public class MyController {
    @Autowired
    UserRepo repo;
    @Autowired
    ContactRepo ContRepo;
    @RequestMapping({"/home","/"})
    public String home()
    {
        return "index.html";
    }
    @RequestMapping("/services")
    public String service()
    {
        return "services.html";
    }
    @RequestMapping("/portfolio")
    public String portfolio()
    {
        return "portfolio.html";
    }
    @RequestMapping("/testimonials")
    public String testomoniols()
    {
        return "testimonials.html";
    }
    @RequestMapping("/team")
    public String team()
    {
        return "team.html";
    }
    @RequestMapping("/news")
    public String news()
    {
        return "news.html";
    }
    @RequestMapping("/quotes")
    public String quotes()
    {
        return "quotes.html";
    }
    @RequestMapping("/about")
    public String about()
    {
        return "about.html";
    }
    @RequestMapping("/pricing")
    public String pricing()
    {
        return "pricing.html";
    }
    @RequestMapping("/faq")
    public String faq()
    {
        return "faq.html";
    }
    @RequestMapping("/terms")
    public String terms()
    {
        return "terms.html";
    }
    @RequestMapping("/privacy")
    public String privacy()
    {
        return "privacy.html";
    }
    @RequestMapping("/blogs")
    public String blogs()
    {
        return "blogs.html";
    }
    @RequestMapping("/blogDetails")
    public String blogDetails()
    {
        return "blogDetails.html";
    }
    @RequestMapping("/signup")
    public String signup()
    {
        return "signup.html";
    }
    
    @PostMapping("/signup")
    public String addUser(MainUser user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword())); 
        repo.save(user);
        return "/saveUser";
    }
    
    @RequestMapping("/search")
    public String search()
    {
        return "search.html";
    }
     @RequestMapping("/saveUser")
    public String saveUser()
    {
        return "saveUser.html";
    }
     @RequestMapping("/contactSave")
    public String contactsave()
    {
        return "saveContactSuccess.html";
    }
    
   

    
    @PostMapping("/search")
    public String searchContact(@RequestParam("number")String number,Model model)
    {
      Contact cont = ContRepo.findContactByNumber(number);
      if(cont!=null)
      {
        model.addAttribute("cont",cont);
        return "foundContact.html";
      }
      else
      {
        return "notFoundContact.html";
      }
    }
}
