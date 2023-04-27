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
    public List<CartItemForm> getCartItems(Integer userId) {
        List<CartItem> cartitems = cartDao.getCartItems(userId);
        //change cartitems to cartitemforms
        List<CartItemForm> cartItemForms = new java.util.ArrayList<>();
        for(CartItem cartItem : cartitems){
            CartItemForm cartItemForm = new CartItemForm();
            cartItemForm.setCart_item_id(cartItem.getId());
            cartItemForm.setBook_id(cartItem.getBook().getId());
            cartItemForm.setTitle(cartItem.getBook().getTitle());
            cartItemForm.setNumber(cartItem.getNumber());
            cartItemForm.setAuthor(cartItem.getBook().getAuthor());
            cartItemForm.setPrice(cartItem.getBook().getPrice());
            cartItemForm.setImage(cartItem.getBook().getImage());
            cartItemForms.add(cartItemForm);
        }
        return cartItemForms;
    }
}
