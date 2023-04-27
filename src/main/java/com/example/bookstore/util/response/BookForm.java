package com.example.bookstore.util.response;

public class BookForm {
    private Long id;

    private String title;

    private String author;

    private String image;

    private Double price;

    private String publisher;

    private String introduction;

    public BookForm(Long book_id, String title, String author, String image, Double price, String publisher, String introduction) {
        this.id = book_id;
        this.title = title;
        this.author = author;
        this.image = image;
        this.price = price;
        this.publisher = publisher;
        this.introduction = introduction;
    }

    public BookForm() {
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

    public void setId(Long book_id) {
        this.id = book_id;
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
}
