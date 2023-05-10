package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.request.BookStorageForm;
import com.example.bookstore.util.response.BookForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Book> bookList = bookDao.findAll();
        List<BookForm> bookFormList = new ArrayList<>();
        for(Book book : bookList) {
            BookForm bookForm = new BookForm();
            bookForm.setId(book.getId());
            bookForm.setTitle(book.getTitle());
            bookForm.setAuthor(book.getAuthor());
            bookForm.setPrice(book.getPrice());
            bookForm.setImage(book.getImage());
            bookForm.setPublisher(book.getPublisher());
            bookForm.setPublisher(book.getPublisher());
            bookFormList.add(bookForm);
        }
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
}
