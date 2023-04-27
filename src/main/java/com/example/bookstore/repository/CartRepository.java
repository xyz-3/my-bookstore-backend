package com.example.bookstore.repository;

import com.example.bookstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    CartItem getCartItemById(Long cart_item_id);

    CartItem findCartItemById(Long cart_item_id);

    void deleteCartItemById(Long cart_item_id);

    CartItem getCartItemByAdder_IdAndBook_Id(Integer user_id, Long book_id);
}
