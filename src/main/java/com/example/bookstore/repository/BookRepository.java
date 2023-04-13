package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{
    //find by id
    Optional<Book> findById(Long id);
    Book getById(Long id);


    //find by title
    Optional<Book> findByTitle(String title);
    Book getByTitle(String title);


    //find by author
    Optional<Book> findByAuthor(String author);
    Book getByAuthor(String author);

    //find all
    List<Book> findAll();


}
