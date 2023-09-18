package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.*;
import com.example.bookstore.util.request.OrderForm;
import jakarta.transaction.Transactional;
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
    @Autowired
    private final CartRepository cartRepository;

    public OrderDaoImpl(OrderItemRepository orderItemRepository, UserRepository userRepository, BookRepository bookRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }


    @Override
    @Transactional
    public Boolean purchaseBookDirectly(Long bookId, Integer userId, Integer quantity) {
        User user = userRepository.findById(userId);
        Book book = bookRepository.findById(bookId).get();
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

    @Override
    @Transactional
    public void addCartOrder(Integer userId, List<Long> cartItemIds) {
        User user = userRepository.findById(userId);
        if(user == null) return;
        Order order = new Order();
        order.setUser(user);
        order.setTime(new Date());
        orderRepository.save(order);
        for(Long cartItemId : cartItemIds) {
            OrderItem orderItem = new OrderItem();
            CartItem cartItem = cartRepository.getCartItemById(cartItemId);

            Book book = cartItem.getBook();
            if(book == null) return;
            if(book.getStock() < cartItem.getNumber()) return;
            book.setStock(book.getStock() - cartItem.getNumber());
            bookRepository.save(book);

            orderItem.setBook(book);
            orderItem.setQuantity(Math.toIntExact(cartItem.getNumber()));
            orderItem.setPrice(cartItem.getBook().getPrice() * cartItem.getNumber());
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
