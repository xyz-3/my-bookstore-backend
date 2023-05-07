package com.example.bookstore.util.response;

import com.example.bookstore.entity.OrderItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class OrderResponseForm {
    private String time;

    private Long userId;

    private Double total_price;

    private List<OrderItemForm> orderItemForms;

    public void calculateTotalPrice(){
        Double totalPrice = 0.0;
        for(OrderItemForm orderItemForm : orderItemForms){
            totalPrice += orderItemForm.getBook_price() * orderItemForm.getAmount();
        }
        this.total_price = totalPrice;
    }


    public OrderResponseForm(List<OrderItem> orderItemForms, Long user_id, Date time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        this.time = sdf.format(time);
        List<OrderItemForm> orderItemFormList = new ArrayList<>();
        for(OrderItem orderItem : orderItemForms){
            orderItemFormList.add(new OrderItemForm(orderItem));
        }
        this.orderItemForms = orderItemFormList;
//        setOrderItemsTime();
        calculateTotalPrice();
        this.userId = user_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public void setOrderItemForms(List<OrderItemForm> orderItemForms) {
        this.orderItemForms = orderItemForms;
    }

    public String getTime() {
        return time;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public List<OrderItemForm> getOrderItemForms() {
        return orderItemForms;
    }
}
