package com.example.myBookShopApp.controllers;

import com.example.myBookShopApp.data.Book;
import com.example.myBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CartController {

    private final BookService bookService;

    @Autowired
    public CartController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("cartData")
    public List<Book> cartData(){
        return bookService.getBooksData();
    }

    @GetMapping("/cart")
    public String cartPage(){
        return "/cart";
    }
}
