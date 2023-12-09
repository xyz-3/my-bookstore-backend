package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.BookTag;
import com.example.bookstore.repository.BookTagRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.response.BookForm;
import com.example.bookstore.util.response.GraphBook;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    private BookTagRepository bookTagRepository;

    @RequestMapping(value = "/api/book/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/api/books/search", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
    public List<Book> searchBooksByTag(@RequestBody String tag) {
        return bookService.searchBooksByTag(tag);
    }

    @RequestMapping(value = "/neo4j", method = RequestMethod.GET)
    public List<Book> testNeo4j() {
        bookTagRepository.deleteAll();


        BookTag booktag1 = new BookTag();
        booktag1.setTag("经典文学");
        BookTag booktag2 = new BookTag();
        booktag2.setTag("社会与政治");
        BookTag booktag3 = new BookTag();
        booktag3.setTag("历史与叙事");
        BookTag booktag4 = new BookTag();
        booktag4.setTag("诗歌");
        BookTag booktag5 = new BookTag();
        booktag5.setTag("心理与存在主义");
        BookTag booktag6 = new BookTag();
        booktag6.setTag("成长与自我发现");

        booktag1.addBookId(3L);
        booktag2.addBookId(4L);
        booktag1.addBookId(5L);
        booktag2.addBookId(6L);
        booktag3.addBookId(7L);
        booktag4.addBookId(8L);
        booktag2.addBookId(9L);
        booktag5.addBookId(10L);
        booktag6.addBookId(11L);

        booktag1.addRelatedTag(booktag4);
        booktag1.addRelatedTag(booktag5);
        booktag2.addRelatedTag(booktag3);
        booktag2.addRelatedTag(booktag5);
        booktag3.addRelatedTag(booktag1);
        booktag4.addRelatedTag(booktag6);
        booktag5.addRelatedTag(booktag6);

        bookTagRepository.save(booktag1);
        bookTagRepository.save(booktag2);
        bookTagRepository.save(booktag3);
        bookTagRepository.save(booktag4);
        bookTagRepository.save(booktag5);
        bookTagRepository.save(booktag6);

        return null;
    }

    @QueryMapping
    public GraphBook bookByTitle(@Argument String title) {
        Book book = bookService.getBookByTitle(title);
        if (book == null) {
            return null;
        }
        return new GraphBook(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice());
    }
}
