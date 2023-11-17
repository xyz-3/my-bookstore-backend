package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.request.BookStorageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.findBookById(id);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookDao.findBookByTitle(title).orElseThrow();
    }


    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = bookDao.findAll();
        return bookList;
    }

    @Override
    public Boolean setBookInfo(Long id, BookStorageForm bookStorageForm) {
        return bookDao.setBookInfo(id, bookStorageForm);
    }


    @Override
    public List<Book> deleteBook(Long id) {
        return bookDao.deleteById(id);
    }

    @Override
    public Long addBook(BookStorageForm bookStorageForm) {
        return bookDao.addBook(bookStorageForm);
    }

    @Override
    public List<Book> searchBooksByTag(String tag) {
        return bookDao.searchBooksByTag(tag);
    }
}
