package com.example.bookstore.service.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.Order;
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
    public Boolean purchaseBookDirectly(Long bookId, Integer userId, Integer quantity) {
        return orderDao.purchaseBookDirectly(bookId, userId, quantity);
    }

    @Override
    public List<Order> getOrders(Integer id) {
        return orderDao.getOrders(id);
    }

    @Override
    public List<OrderItem> getOrderItems(Long id) {
        return orderDao.getOrderItems(id);
    }

    @Override
    public void addCartOrder(Integer userId, List<Long> cartItemIds) {
        orderDao.addCartOrder(userId, cartItemIds);
    }

    @Override
    public List<Order> getAllOrders(){
        return orderDao.getAllOrders();
    }

}
