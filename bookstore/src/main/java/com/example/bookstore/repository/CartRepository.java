package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    CartItem getCartItemById(Long cart_item_id);

    CartItem findCartItemById(Long cart_item_id);

    void deleteCartItemById(Long cart_item_id);

    CartItem getCartItemByAdder_IdAndBook_Id(Integer user_id, Long book_id);

    CartItem getById(Long cart_item_id);

    List<CartItem> findAllByAdder_Id(Integer user_id);

    List<CartItem> findAllByBook(Book book);
}
