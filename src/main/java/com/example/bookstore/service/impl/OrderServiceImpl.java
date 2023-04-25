package com.example.bookstore.service.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.request.OrderForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void addOrderDirectly(OrderForm orderForm) {
        // TODO Auto-generated method stub
        orderDao.addOrder(orderForm);
    }

    @Override
    public List<OrderItem> getAllOrders() {
        // TODO Auto-generated method stub
        return orderDao.findAll();
    }
}
