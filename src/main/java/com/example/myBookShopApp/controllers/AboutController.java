package com.example.myBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String recentPage(Model model){
        model.addAttribute("activeItem", "about");
        return "/about";
    }
}
