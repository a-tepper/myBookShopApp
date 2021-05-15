package com.example.myBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsController {

    @GetMapping("/contacts")
    public String recentPage(Model model){
        model.addAttribute("activeItem", "contacts");
        return "/contacts";
    }
}
