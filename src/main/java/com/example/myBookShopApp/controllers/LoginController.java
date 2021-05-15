package com.example.myBookShopApp.controllers;

import com.example.myBookShopApp.data.Book;
import com.example.myBookShopApp.data.BookService;
import com.example.myBookShopApp.data.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/signin")
    public String recentPage(Model model){
        model.addAttribute("activeItem", "signin");
        return "/signin";
    }
}
