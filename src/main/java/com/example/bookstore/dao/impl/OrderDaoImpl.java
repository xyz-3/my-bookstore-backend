package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderItemRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.request.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final OrderRepository orderRepository;

    public OrderDaoImpl(OrderItemRepository orderItemRepository, UserRepository userRepository, BookRepository bookRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
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
        return orderItemRepository.findAll();
    }


    @Override
    public Boolean purchaseBookDirectly(Long bookId, Integer userId, Integer quantity) {
        User user = userRepository.findById(userId);
        Book book = bookRepository.getById(bookId);
        if(user == null || book == null) return false;

        if(book.getStock() < quantity) return false;

        book.setStock(book.getStock() - quantity);
        bookRepository.save(book);

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
}
