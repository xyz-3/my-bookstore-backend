package com.example.bookstore.util.response;

import lombok.Data;

@Data
public class GraphBook {
    private Long id;
    private String title;
    private String author;
    private Double price;
    public GraphBook(Long id, String title, String author, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public GraphBook() {
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Double getPrice() {
        return price;
    }
    public void setId(Long book_id) {
        this.id = book_id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author= author;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
