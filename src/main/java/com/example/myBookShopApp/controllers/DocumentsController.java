package com.example.myBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentsController {

    @GetMapping("/documents")
    public String recentPage(Model model){
        model.addAttribute("activeItem", "documents");
        return "/documents/index";
    }
}
