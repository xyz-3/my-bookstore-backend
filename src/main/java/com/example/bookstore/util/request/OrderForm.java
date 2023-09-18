package com.example.bookstore.util.request;

public class OrderForm {
    private Long bookId;

    private Integer quantity;

    private Integer userId;


    public Long getBookId() {
        return bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "bookId=" + bookId +
                ", quantity=" + quantity +
                ", userId=" + userId +
                '}';
    }
}
