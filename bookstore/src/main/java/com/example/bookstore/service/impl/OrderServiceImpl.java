package com.example.bookstore.service.impl;

import com.example.bookstore.dao.*;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean purchaseBookDirectly(Long bookId, Integer userId, Integer quantity) {
        User user = userDao.getUserById(Long.valueOf(userId));
        Book book = bookDao.updateStock(bookId, quantity);
        return orderDao.purchaseBookDirectly(book, user, quantity);
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addCartOrder(Integer userId, List<Long> cartItemIds) {
        Order order = orderDao.addCartOrder(userId, cartItemIds);
        orderItemDao.addCartOrder(order, cartItemIds);
        try{
            cartDao.deleteCartItem(userId, cartItemIds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders(){
        return orderDao.getAllOrders();
    }

}
