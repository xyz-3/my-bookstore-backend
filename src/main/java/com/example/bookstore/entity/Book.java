package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"cartItemSet", "orderItemSet"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 128, nullable = false)
    private String title;
    @Column(name = "author", length = 64, nullable = false)
    private String author;

    @Column(name = "introduction", length = 2048)
    private String introduction;

    @Column(name = "image", length = 16384)
    private String image;

    @Column(name = "price", length = 8, nullable = false)
    private Double price;

    @Column(name = "publisher", length = 64, nullable = false)
    private String publisher;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"book"})
    private List<CartItem> cartItemSet;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"book"})
    private List<OrderItem> orderItemSet;


    public Book(String title, String author, String introduction, String image, Double price, String publisher) {
        this.title = title;
        this.author = author;
        this.introduction = introduction;
        this.image = image;
        this.price = price;
        this.publisher = publisher;
    }

    public Book() {
    }


}
