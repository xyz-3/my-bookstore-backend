package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.request.OrderForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    private final BookService bookService;

    public OrderController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    //add order directly
    @RequestMapping(value = "/purchase/direct", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public void addOrder(@RequestBody @NotNull OrderForm orderForm) {
        orderService.addOrderDirectly(orderForm);
    }

    //get all order
    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<OrderItem> getAllOrders() {
        return orderService.getAllOrders();
    }
}
