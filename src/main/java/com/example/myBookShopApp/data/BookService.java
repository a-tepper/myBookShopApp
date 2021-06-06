package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    //NEW BOOK SEVICE METHODS

    public List<Book> getBooksByAuthor(String authorName){
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title){
        return bookRepository.findBooksByTitleContaining(title);
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max){
        return bookRepository.findBooksByPriceOldBetween(min,max);
    }

    public List<Book> getBooksWithPrice(Integer price){
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice(){
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers(){
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findBookByTitleContaining(searchWord,nextPage);
    }

    public Page<Book> getPageOfRecentBooks(Integer offset, Integer limit, String fromStr, String toStr) {
        Pageable nextPage = PageRequest.of(offset,limit);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date from = formatter.parse(fromStr);
            Date to = formatter.parse(toStr);
            return bookRepository.findByPubDateBetweenOrderByPubDateDesc(from, to, nextPage);
        }
        catch(Exception e) {
            return bookRepository.findByOrderByPubDateDesc(nextPage);
        }
    }

    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset,limit);
        List<Book> books = this.bookRepository.findByIsBestsellerIs(1).stream().sorted(Comparator.comparingDouble(Book::getPopularity).reversed())
                .collect(Collectors.toList());
        return new PageImpl<>(books.subList(limit*offset, limit*offset + limit), nextPage, books.size());
    }

    public Page<Book> getPageOfAuthorBooks(Integer offset, Integer limit, Object authorId) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBooksByAuthor_Id(authorId, nextPage);
    }
}
