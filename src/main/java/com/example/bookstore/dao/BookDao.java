package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findBookById(Long id);

    Optional<Book> findBookByTitle(String title);

    List<Book> findAll();
}
