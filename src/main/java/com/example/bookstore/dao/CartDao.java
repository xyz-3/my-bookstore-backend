package com.example.bookstore.dao;

import com.example.bookstore.entity.CartItem;

import java.util.List;

public interface CartDao {
    Boolean addBookToCart(Long bookId, Integer userId, Long number);

    List<CartItem> getCartItems(Integer userId);

    List<CartItem> deleteCartItem(Integer userId, Long cartId);

    List<CartItem> updateCartItem(Integer userId, Long cartId, Long number);

    List<CartItem> deleteCartItem(Integer userId, List<Long> cartIds);
}
