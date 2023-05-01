package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.util.response.BookForm;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    List<Book> getAllBooks();


}
