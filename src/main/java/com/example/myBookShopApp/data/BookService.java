package com.example.myBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class BookService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData(){
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum)->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList<>(books);
    }
    public List<Author> getAllAuthors() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors",
                (ResultSet rs, int rowNum) -> {
                    Author author = new Author();
                    author.setId(rs.getInt("id"));
                    author.setName(rs.getString("name"));
                    return author;
                });
        return new ArrayList<>(authors);
    }

    public HashMap<String, List<Author>> getAuthorsByFirstLetter(){
        List<Author> authors = getAllAuthors();
        HashMap<String, List<Author>> authorsByLetter = new HashMap<>();
        for (Author author: authors) {
            String firstLetter = author.getName().substring(0,1);
            if (authorsByLetter.containsKey(firstLetter)) {
                authorsByLetter.get(firstLetter).add(author);
            }
            else {
                authorsByLetter.put(firstLetter, new ArrayList<>(Arrays.asList(author)));
            }
        }
        return authorsByLetter;
    }
}
