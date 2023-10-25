package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.OrderForm;

import java.util.List;

public interface OrderDao {

    List<OrderItem> findAll();

    Boolean purchaseBookDirectly(Book book, User user, Integer quantity);

    List<Order> getOrders(Integer user_id);

    List<OrderItem> getOrderItems(Long order_id);

    Order addCartOrder(Integer userId, List<Long> cartItemIds);

    List<Order> getAllOrders();
}
