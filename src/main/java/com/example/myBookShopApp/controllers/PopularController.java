package com.example.myBookShopApp.controllers;

import com.example.myBookShopApp.data.AuthorService;
import com.example.myBookShopApp.data.Book;
import com.example.myBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PopularController {

    private final BookService bookService;

    @Autowired
    public PopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks(){
        return bookService.getBooksData();
    }

    @GetMapping("/popular")
    public String popularPage(){
        return "/books/popular";
    }
}
