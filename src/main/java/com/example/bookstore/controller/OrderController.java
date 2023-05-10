package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.request.OrderForm;
import com.example.bookstore.util.response.OrderResponseForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    private final BookService bookService;

    public OrderController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    //get all order
    @RequestMapping(value = "order/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<OrderResponseForm> getOrders(@PathVariable("id") Integer id) {
        List<Order> orders = orderService.getOrders(id);
        List<OrderResponseForm> orderForms = new ArrayList<>();
        for(Order order : orders){
            List<OrderItem> cur_order_items = orderService.getOrderItems(order.getId());
            Long user_id = order.getUser().getId();
            String user_name = order.getUser().getUsername();
            Date time = order.getTime();
            OrderResponseForm orderForm = new OrderResponseForm(cur_order_items, user_id, time, user_name);
            orderForms.add(orderForm);
        }
        return orderForms;
    }


    //purchase a book directly from book detail page
    @RequestMapping(value = "book/purchaseDirectly", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Boolean purchaseBookDirectly(@RequestBody @NotNull OrderForm orderForm) {
        Long bookId = orderForm.getBookId();
        Integer userId = orderForm.getUserId();
        Integer quantity = orderForm.getQuantity();
        return orderService.purchaseBookDirectly(bookId, userId, quantity);
    }
}
