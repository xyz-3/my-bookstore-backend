package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.response.BookForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/api/book/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
