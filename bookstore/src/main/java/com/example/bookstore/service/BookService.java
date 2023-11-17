package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.util.request.BookStorageForm;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);

    Book getBookByTitle(String title);

    List<Book> getAllBooks();

    Boolean setBookInfo(Long id, BookStorageForm bookStorageForm);

    List<Book> deleteBook(Long id);

    Long addBook(BookStorageForm bookStorageForm);

    List<Book> searchBooksByTag(String tag);
}
