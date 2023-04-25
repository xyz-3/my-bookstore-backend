package com.example.bookstore.repository;

import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();
}
