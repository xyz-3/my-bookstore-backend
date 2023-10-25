package com.example.bookstore.service.impl;

import com.example.bookstore.dao.CartDao;
import com.example.bookstore.entity.CartItem;
import com.example.bookstore.service.CartService;
import com.example.bookstore.util.response.CartItemForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartDao cartDao;

    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public Boolean addBookToCart(Long bookId, Integer userId, Long number) {
        return cartDao.addBookToCart(bookId, userId, number);
    }

    @Override
    public List<CartItem> getCartItems(Integer userId) {
        List<CartItem> cartitems = cartDao.getCartItems(userId);
        return cartitems;
    }


    @Override
    public List<CartItem> deleteCartItem(Integer userId, Long cartId) {
        return cartDao.deleteCartItem(userId, cartId);
    }


    @Override
    public List<CartItem> updateCartItem(Integer userId, Long cartId, Long number) {
        return cartDao.updateCartItem(userId, cartId, number);
    }

    @Override
    public List<CartItem> deleteCartItem(Integer userId, List<Long> cartIds) {
        return cartDao.deleteCartItem(userId, cartIds);
    }

}
