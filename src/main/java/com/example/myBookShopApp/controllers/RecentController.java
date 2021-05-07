package com.example.myBookShopApp.controllers;

import com.example.myBookShopApp.data.Book;
import com.example.myBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class RecentController {

    private final BookService bookService;

    @Autowired
    public RecentController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks(){
        return bookService.getBooksData();
    }

    @GetMapping("/recent")
    public String recentPage(){
        return "/books/recent";
    }
}
