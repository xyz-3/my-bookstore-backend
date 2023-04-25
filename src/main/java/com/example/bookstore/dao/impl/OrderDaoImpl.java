package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.util.request.OrderForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;

    public OrderDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(OrderForm orderForm) {
//        OrderItem orderItem = new OrderItem();
//        System.out.println("add a new order: " + orderItem);
//        orderItem.setBookId(orderForm.getBookId());
//        orderItem.setUserId(orderForm.getUserId());
//        orderItem.setQuantity(orderForm.getQuantity());
//        orderRepository.save(orderItem);

    }

    @Override
    public List<OrderItem> findAll() {
        return orderRepository.findAll();
    }
}
