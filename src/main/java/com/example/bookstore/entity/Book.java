package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 128, nullable = false)
    private String title;
    @Column(name = "author", length = 64, nullable = false)
    private String author;

    @Column(name = "introduction", length = 2048)
    private String introduction;

    @Column(name = "image", length = 16384)
    private String image;

//    @Column(name = "ISBN", length = 32, nullable = false, unique = true)
//    private String ISBN;

    @Column(name = "price", length = 8, nullable = false)
    private Double price;

    @Column(name = "publisher", length = 64, nullable = false)
    private String publisher;


    public Book(String title, String author, String introduction, String image, Double price, String publisher) {
        this.title = title;
        this.author = author;
        this.introduction = introduction;
        this.image = image;
//        this.ISBN = ISBN;
        this.price = price;
        this.publisher = publisher;
    }

    public Book() {
    }


}
