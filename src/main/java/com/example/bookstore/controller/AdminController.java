package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.request.BookStorageForm;
import com.example.bookstore.util.response.OrderResponseForm;
import com.example.bookstore.util.response.UserInfoForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
public class AdminController {

    @Autowired
    private final BookService bookService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final UserService userService;

    public AdminController(BookService bookService, OrderService orderService, UserService userService) {
        this.bookService = bookService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/api/storage", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getStorage(){
        List<Book> ret = bookService.getAllBooks();
        return bookService.getAllBooks();
    }


    @RequestMapping(value = "/storage/add", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Long addStorage(@RequestBody @NotNull BookStorageForm bookStorageForm){
        return bookService.addBook(bookStorageForm);
    }

    @RequestMapping(value = "/api/storage/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean changeBookStorageInfo(@PathVariable("id") Long id, @RequestBody @NotNull BookStorageForm bookStorageForm){
        return bookService.setBookInfo(id, bookStorageForm);
    }

    @RequestMapping(value = "/storage/delete/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> deleteBook(@PathVariable("id") Long id){
        return bookService.deleteBook(id);
//        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<OrderResponseForm> getOrders(){
        List<Order> allOrders = orderService.getAllOrders();
        List<OrderResponseForm> allOrderForms = new ArrayList<>();
        for(Order order : allOrders){
            List<OrderItem> cur_order_items = orderService.getOrderItems(order.getId());
            Long user_id = order.getUser().getId();
            String user_name = order.getUser().getUsername();
            Date time = order.getTime();
            OrderResponseForm orderForm = new OrderResponseForm(cur_order_items, user_id, time, user_name);
            allOrderForms.add(orderForm);
        }
        return allOrderForms;
    }


    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<UserInfoForm> getUsers(){
        List<User> users = userService.getAllUsers();
        List<UserInfoForm> userInfoForms = new ArrayList<>();
        for(User user : users){
            UserInfoForm userInfoForm = new UserInfoForm(user);
            userInfoForms.add(userInfoForm);
        }
        return userInfoForms;
    }
}
