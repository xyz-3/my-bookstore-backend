package com.example.bookstore.repository;

import com.example.bookstore.entity.BookDescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookDescriptionRepository extends MongoRepository<BookDescription, String> {
    BookDescription findBookDescriptionByBookId(Long bookId);
}
