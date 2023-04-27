package com.example.bookstore.util.response;

public class CartItemForm {
    private Long cart_item_id;

    private Long book_id;

    private String title;

    private String author;

    private String image;

    private Long number;

    private Double price;

    public CartItemForm(Long cart_item_id, Long book_id, String title, String author, String image, Long number, Double price) {
        this.cart_item_id = cart_item_id;
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.image = image;
        this.number = number;
        this.price = price;
    }

    public CartItemForm() {
    }

    public Long getCart_item_id() {
        return cart_item_id;
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

    public Long getNumber() {
        return number;
    }

    public Long getBook_id() {
        return book_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setCart_item_id(Long cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
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

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
