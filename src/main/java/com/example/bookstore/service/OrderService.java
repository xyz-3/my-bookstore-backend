package com.example.bookstore.service;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.util.request.OrderForm;

import java.util.List;

public interface OrderService {
//    void addOrderDirectly(OrderForm orderForm);

    Boolean purchaseBookDirectly(Long bookId, Integer userId, Integer quantity);

    List<Order> getOrders(Integer id);

    List<OrderItem> getOrderItems(Long id);

    void addCartOrder(Integer userId, List<Long> cartItemIds);

    List<Order> getAllOrders();
}
