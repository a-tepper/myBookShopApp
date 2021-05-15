package com.example.myBookShopApp.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresController {

    @GetMapping("/genres")
    public String genresPage(Model model){
        model.addAttribute("activeItem", "genres");
        return "/genres/index";
    }
}
