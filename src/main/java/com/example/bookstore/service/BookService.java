package com.example.bookstore.service;

import com.example.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    List<Book> getAllBooks();
}
