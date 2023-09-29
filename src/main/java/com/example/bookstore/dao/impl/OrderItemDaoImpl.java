package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderItemDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void addCartOrder(Order order, List<Long> cartItemIds){
        for(Long cartItemId : cartItemIds) {
            OrderItem orderItem = new OrderItem();
            CartItem cartItem = cartRepository.getCartItemById(cartItemId);
            if(cartItem == null) continue;

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
}
