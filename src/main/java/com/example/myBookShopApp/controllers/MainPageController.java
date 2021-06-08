package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() {
        return bookService.getPageOfRecentBooks(0, 6, null, null).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookService.getPageOfPopularBooks(0, 6).getContent();
    }

    @ModelAttribute("genreMap")
    public Map<ParentGenre, List<Genre>> genreMap() {
        return bookService.getGenreMap();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getRecommendedBooksPage(@RequestParam("offset") Integer offset,
                                                @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestParam("from") String from,
                                           @RequestParam("to") String to) {
        return new BooksPageDto(bookService.getPageOfRecentBooks(offset, limit, from, to).getContent());
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit) {
        return new BooksPageDto(bookService.getPageOfPopularBooks(offset, limit).getContent());
    }

    @GetMapping("/books/author/{authorId}")
    @ResponseBody
    public BooksPageDto getAuthorBooks(@RequestParam("offset") Integer offset,
                                       @RequestParam("limit") Integer limit,
                                       @PathVariable(value = "authorId", required = true) Integer authorId) {
        return new BooksPageDto(bookService.getPageOfAuthorBooks(offset, limit, authorId).getContent());
    }

    @GetMapping("/books/genre/{id}")
    @ResponseBody
    public BooksPageDto getGenreBooks(@RequestParam("offset") Integer offset,
                                       @RequestParam("limit") Integer limit,
                                       @PathVariable(value = "id", required = true) Integer id) {
        return new BooksPageDto(bookService.getPageOfGenreBooks(offset, limit, id).getContent());
    }

    @GetMapping("/books/parent_genre/{id}")
    @ResponseBody
    public BooksPageDto getParentGenreBooks(@RequestParam("offset") Integer offset,
                                      @RequestParam("limit") Integer limit,
                                      @PathVariable(value = "id", required = true) Integer id) {
        return new BooksPageDto(bookService.getPageOfParentGenreBooks(offset, limit, id).getContent());
    }

    @GetMapping("/genre/{id}")
    public String genrePage(Model model, @PathVariable("id") Integer id){
        List<Book> books = bookService.getPageOfGenreBooks(0, 6, id).getContent();
        model.addAttribute("genreBooks", books);
        model.addAttribute("genre", bookService.getGenreById(id));
        model.addAttribute("load", "genre");
        return "/genres/slug";
    }

    @GetMapping("/parent_genre/{id}")
    public String parentGenrePage(Model model, @PathVariable("id") Integer id){
        List<Book> books = bookService.getPageOfParentGenreBooks(0, 6, id).getContent();
        model.addAttribute("genreBooks", books);
        model.addAttribute("genre", bookService.getParentGenreById(id));
        model.addAttribute("load", "parent_genre");
        return "/genres/slug";
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }

    @GetMapping("/recent")
    public String recentPage() {
        return "/books/recent";
    }

    @GetMapping("/popular")
    public String popularPage() {
        return "/books/popular";
    }

    @GetMapping("/genres")
    public String genresPage() {
        return "/genres/index";
    }
}
