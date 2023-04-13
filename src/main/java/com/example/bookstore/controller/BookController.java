package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/api/book/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}