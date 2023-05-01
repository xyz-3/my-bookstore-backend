package com.example.bookstore.util.response;

import com.example.bookstore.entity.OrderItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderItemForm {
    private String book_cover;

    private String book_title;

    private Integer amount;

    private Double book_price;

    public void setBook_cover(String book_cover) {
        this.book_cover = book_cover;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setBook_price(Double book_price) {
        this.book_price = book_price;
    }

    public String getBook_cover() {
        return book_cover;
    }

    public String getBook_title() {
        return book_title;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getBook_price() {
        return book_price;
    }

    public OrderItemForm(){}

    public OrderItemForm(OrderItem orderItem){
        this.amount = orderItem.getQuantity();
        this.book_cover = orderItem.getBook().getImage();
        this.book_title = orderItem.getBook().getTitle();
        this.book_price = orderItem.getBook().getPrice();
    }
}
