package com.example.bookstore.service;

import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.util.request.OrderForm;

import java.util.List;

public interface OrderService {
    void addOrderDirectly(OrderForm orderForm);

    List<OrderItem> getAllOrders();
}
