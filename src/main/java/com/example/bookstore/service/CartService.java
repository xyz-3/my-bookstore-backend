package com.example.bookstore.service;

import com.example.bookstore.util.response.CartItemForm;

import java.util.List;

public interface CartService {

    Boolean addBookToCart(Long bookId, Integer userId, Long number);

    List<CartItemForm> getCartItems(Integer userId);
}
