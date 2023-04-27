package com.example.bookstore.controller;

import com.example.bookstore.service.CartService;
import com.example.bookstore.util.request.CartAddForm;
import com.example.bookstore.util.response.CartItemForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @RequestMapping(value = "/book/addtocart", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Boolean addBookToCart(@RequestBody @NotNull CartAddForm cartAddForm) {
        Long bookId = cartAddForm.getBook_id();
        Integer userId = cartAddForm.getAdder_id();
        Long number = cartAddForm.getNumber();
        System.out.println("bookId: " + bookId + " userId: " + userId + " number: " + number);
        return cartService.addBookToCart(bookId, userId, number);
    }

    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<CartItemForm> getCartItems(@PathVariable("id") Integer id) {
        return cartService.getCartItems(id);
    }

}
