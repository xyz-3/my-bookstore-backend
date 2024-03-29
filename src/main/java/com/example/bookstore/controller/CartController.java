package com.example.bookstore.controller;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.service.CartService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.msgutils.MsgCode;
import com.example.bookstore.util.msgutils.MsgUtil;
import com.example.bookstore.util.request.CartAddForm;
import com.example.bookstore.util.response.CartItemForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Transactional
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping(value = "/book/addtocart", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Boolean addBookToCart(@RequestBody @NotNull CartAddForm cartAddForm) {
        Long bookId = cartAddForm.getBook_id();
        Integer userId = cartAddForm.getAdder_id();
        Long number = cartAddForm.getNumber();
        return cartService.addBookToCart(bookId, userId, number);
    }

    @RequestMapping(value = "/api/cart/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public List<CartItemForm> getCartItems(@PathVariable("id") Integer id) {
        List<CartItemForm> cartItemForms = new java.util.ArrayList<>();
        List<CartItem> cartItems = cartService.getCartItems(id);
        for(CartItem cartItem : cartItems){
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


    @RequestMapping(value = "api/cart/delete/{user_id}/{cart_id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public List<CartItemForm> deleteCartItem(@PathVariable("user_id") Integer userId, @PathVariable("cart_id") Long cartId) {
        List<CartItem> cartItems = cartService.deleteCartItem(userId, cartId);
        List<CartItemForm> cartItemForms = new java.util.ArrayList<>();
        for(CartItem cartItem : cartItems){
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

    @RequestMapping(value = "api/cart/update/{user_id}/{cart_id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public List<CartItemForm> updateCartItem(@PathVariable("user_id") Integer userId, @PathVariable("cart_id") Long cartId, @RequestBody @NotNull Long number){
        List<CartItem> cartItems = cartService.updateCartItem(userId, cartId, number);
        List<CartItemForm> cartItemForms = new java.util.ArrayList<>();
        for(CartItem cartItem : cartItems){
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

    @RequestMapping(value = "/api/cart/purchase/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Msg cartPurchase(@PathVariable("id") Integer userId, @RequestBody List<Long> cartItemIds){
        //add cart items to order table
        String order_uuid = UUID.randomUUID().toString().toUpperCase();
        JSONObject data = new JSONObject();
        data.put("uuid", order_uuid);
        JSONObject msgData = new JSONObject();
        msgData.put("userId", userId);
        msgData.put("cartItemIds", cartItemIds);
        kafkaTemplate.send("cartOrder", order_uuid, msgData.toString());
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
    }

}
