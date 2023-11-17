package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "book", uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
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

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "image", length = 16384)
    private String image;

    @Column(name = "price", length = 8, nullable = false)
    private Double price;

    @Column(name = "publisher", length = 64, nullable = false)
    private String publisher;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "tag", nullable = false)
    private String tag;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"book"})
    private List<CartItem> cartItemSet;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"book"})
    private List<OrderItem> orderItemSet;


    public Book(String title, String author, String introduction, String image, Double price, String publisher, String tag) {
        this.title = title;
        this.author = author;
        this.introduction = introduction;
        this.image = image;
        this.price = price;
        this.publisher = publisher;
        this.tag = tag;
    }

    public Book() {
    }


}
