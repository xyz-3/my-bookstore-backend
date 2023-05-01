package com.example.bookstore.repository;

import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();

    List<OrderItem> findAllByOrder_id(Long order_id);
}
