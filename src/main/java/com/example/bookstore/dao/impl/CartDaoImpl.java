package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.CartDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.CartItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.bookstore.repository.CartRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Boolean addBookToCart(Long bookId, Integer userId, Long number) {
        User user = userRepository.findById(userId);
        Book book = bookRepository.getById(bookId);
        if(user == null || book == null){
            return false;
        }
        CartItem cartItem = cartRepository.getCartItemByAdder_IdAndBook_Id(userId, bookId);
        if(cartItem != null){
            Long newNumber = cartItem.getNumber() + number;
            cartItem.setNumber(newNumber);
            cartRepository.save(cartItem);
        }else{
            CartItem newCartItem = new CartItem();
            newCartItem.setAdder(user);
            newCartItem.setBook(book);
            newCartItem.setNumber(number);
            cartRepository.save(newCartItem);
        }
        return true;
    }

    @Override
    public List<CartItem> getCartItems(Integer userId) {
        User user = userRepository.findById(userId);
        if(user == null){
            return null;
        }else{
            return cartRepository.findAllByAdder_Id(userId);
        }
    }

    @Override
    public List<CartItem> deleteCartItem(Integer userId, Long cartId) {
        User user = userRepository.findById(userId);
        if(user == null){
            return null;
        }else{
            CartItem cartItem = cartRepository.getCartItemById(cartId);
            user.getCart().remove(cartItem);
            userRepository.save(user);
            cartRepository.delete(cartItem);
            return user.getCart();
        }
    }

    @Override
    public List<CartItem> updateCartItem(Integer userId, Long cartId, Long number) {
        User user = userRepository.findById(userId);
        if(user == null){
            return null;
        }else{
            CartItem cartItem = cartRepository.getCartItemById(cartId);
            cartItem.setNumber(number);
            cartRepository.save(cartItem);
            return user.getCart();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public List<CartItem> deleteCartItem(Integer userId, List<Long> cartIds) {
        User user = userRepository.findById(userId);
        if(user == null){
            return null;
        }else{
            for(Long cartId : cartIds){
                CartItem cartItem = cartRepository.getCartItemById(cartId);
                user.getCart().remove(cartItem);
                cartRepository.delete(cartItem);
            }
            userRepository.save(user);
            return user.getCart();
        }
    }
}
