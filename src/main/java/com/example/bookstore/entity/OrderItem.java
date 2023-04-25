package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long book_id;

//    @Column(name = "orderId", nullable = false)
//    private Long orderId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

//    @Column(name = "price", nullable = false)
//    private Double price;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    public OrderItem(Long bookId, Integer quantity, Long userId) {
        this.book_id = bookId;
//        this.orderId = orderId;
        this.quantity = quantity;
//        this.price = price;
        this.user_id = userId;
    }

    public OrderItem() {
    }
}
