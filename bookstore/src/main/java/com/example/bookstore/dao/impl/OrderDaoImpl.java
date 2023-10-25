package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public Boolean purchaseBookDirectly(Book book, User user, Integer quantity) {
        if(user == null || book == null) return false;

        Order order = new Order();
        order.setUser(user);
        order.setTime(new Date());
        orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book);
        orderItem.setQuantity(quantity);
        Double total_price = book.getPrice() * quantity;
        orderItem.setPrice(total_price);
        orderItem.setOrder(order);
        orderItemRepository.save(orderItem);
        return true;
    }

    @Override
    public List<Order> getOrders(Integer user_id) {
        User user = userRepository.findById(user_id);
        if(user == null) return null;
        return orderRepository.findAllByUser_id(user_id);
    }
    @Override
    public List<OrderItem> getOrderItems(Long order_id) {
        Order order = orderRepository.findById(order_id).get();
        if(order == null) return null;
        return orderItemRepository.findAllByOrder_id(order_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public Order addCartOrder(Integer userId, List<Long> cartItemIds) {
        User user = userRepository.findById(userId);
        if(user == null) return null;
        Order order = new Order();
        order.setUser(user);
        order.setTime(new Date());
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
