package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.findBookById(id).orElseThrow();
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookDao.findBookByTitle(title).orElseThrow();
    }


    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }
}
