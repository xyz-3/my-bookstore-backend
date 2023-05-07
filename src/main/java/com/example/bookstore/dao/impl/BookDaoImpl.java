package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.util.request.BookStorageForm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Boolean setBookInfo(Long id, BookStorageForm bookStorageForm) {
        Book book = bookRepository.getById(id);
        book.setTitle(bookStorageForm.getTitle());
        book.setAuthor(bookStorageForm.getAuthor());
        book.setPrice(bookStorageForm.getPrice());
        book.setImage(bookStorageForm.getImage());
        book.setPublisher(bookStorageForm.getPublisher());
        book.setStock(bookStorageForm.getStock());
        book.setIntroduction(bookStorageForm.getIntroduction());
        bookRepository.save(book);
        return true;
    }
}
