package com.example.bookstore.dao;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.util.request.OrderForm;

import java.util.List;

public interface OrderDao {
    void addOrder(OrderForm orderForm);

    List<OrderItem> findAll();

    Boolean purchaseBookDirectly(Long bookId, Integer userId, Integer quantity);

    List<Order> getOrders(Integer user_id);

    List<OrderItem> getOrderItems(Long order_id);
}
