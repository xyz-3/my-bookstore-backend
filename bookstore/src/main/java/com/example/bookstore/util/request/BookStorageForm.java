package com.example.bookstore.util.request;

public class BookStorageForm {
    private Long id;
    private String title;
    private String author;
    private String image;
    private Double price;
    private String publisher;
    private String introduction;
    private Long stock;
    private String isbn;

    public BookStorageForm() {
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

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Long getStock() {
        return stock;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
