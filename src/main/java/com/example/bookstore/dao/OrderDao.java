package com.example.bookstore.dao;

import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.util.request.OrderForm;

import java.util.List;

public interface OrderDao {
    void addOrder(OrderForm orderForm);

    List<OrderItem> findAll();
}
