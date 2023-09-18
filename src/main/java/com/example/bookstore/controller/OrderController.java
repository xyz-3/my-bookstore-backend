package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.msgutils.MsgCode;
import com.example.bookstore.util.msgutils.MsgUtil;
import com.example.bookstore.util.request.OrderForm;
import com.example.bookstore.util.response.OrderResponseForm;
import org.jetbrains.annotations.NotNull;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {
    private final OrderService orderService;

    private final BookService bookService;

    public OrderController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //get all order
    @RequestMapping(value = "order/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Msg purchaseBookDirectly(@RequestBody @NotNull OrderForm orderForm) {
        String order_uuid = UUID.randomUUID().toString().toUpperCase();
        JSONObject data = new JSONObject();
        data.put("uuid", order_uuid);
        kafkaTemplate.send("directOrder", order_uuid, orderForm.toString());
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
    }
}
