package com.example.microservice.controller;


import com.example.microservice.entity.Book;
import com.example.microservice.repository.BookRepository;
import com.example.microservice.util.msgutils.Msg;
import com.example.microservice.util.msgutils.MsgCode;
import com.example.microservice.util.msgutils.MsgUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/getBookAuthorByTitle/{title}", method = RequestMethod.GET)
    public String getBookAuthorByTitle(@PathVariable("title") String title) {
        Book book = bookRepository.findBookByTitle(title);
        if (book == null) {
            return "Book not found!";
        } else {
            return book.getTitle() +
                    " by " +
                    book.getAuthor() +
                    ".";
        }
    }
}
