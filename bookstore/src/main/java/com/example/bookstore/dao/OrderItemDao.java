package com.example.bookstore.dao;

import com.example.bookstore.entity.Order;

import java.util.List;

public interface OrderItemDao {
    void addCartOrder(Order order, List<Long> cartItemIds);
}
