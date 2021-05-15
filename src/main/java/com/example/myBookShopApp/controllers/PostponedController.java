package com.example.myBookShopApp.controllers;

import com.example.myBookShopApp.data.Book;
import com.example.myBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PostponedController {

    private final BookService bookService;

    @Autowired
    public PostponedController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("postponedBooks")
    public List<Book> postponedBooks(){
        return bookService.getBooksData();
    }

    @GetMapping("/postponed")
    public String postponedPage(Model model){
        model.addAttribute("activeItem", "postponed");
        return "/postponed";
    }
}
