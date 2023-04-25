package com.example.bookstore.util.request;

public class OrderForm {
    private Long bookId;

    private Integer quantity;

    private Long userId;


    public Long getBookId() {
        return bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getUserId() {
        return userId;
    }
}
