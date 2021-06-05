package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Api(description = "authors data")
public class AuthorsController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("authorsMap")
    public Map<String,List<Author>> authorsMap(){
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "/authors/index";
    }

    @GetMapping("/author/{id}")
    public String authorPage(Model model, @PathVariable("id") Integer id){
        model.addAttribute("author", authorService.getAuthor(id));
        List<Book> books = bookService.getPageOfAuthorBooks(0, 6, id).getContent();
        model.addAttribute("authorBooks", books);
        return "/authors/slug";
    }

    @ApiOperation("method to get map of authors")
    @GetMapping("/api/authors")
    @ResponseBody
    public Map<String,List<Author>> authors(){
        return authorService.getAuthorsMap();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
}
