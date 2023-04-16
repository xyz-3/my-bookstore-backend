package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "orderItem")

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookId", nullable = false)
    private Long bookId;

    @Column(name = "orderId", nullable = false)
    private Long orderId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    public OrderItem(Long bookId, Long orderId, Integer quantity, Double price) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {
    }
}
