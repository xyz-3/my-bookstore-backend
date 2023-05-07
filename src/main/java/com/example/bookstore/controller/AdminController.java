package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.request.BookStorageForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class AdminController {
    private final BookService bookService;

    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/api/storage", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getStorage(){
        List<Book> ret = bookService.getAllBooks();
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/api/storage/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean changeBookStorageInfo(@PathVariable("id") Long id, @RequestBody @NotNull BookStorageForm bookStorageForm){
        return bookService.setBookInfo(id, bookStorageForm);
    }
}
