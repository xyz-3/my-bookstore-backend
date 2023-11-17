package com.example.bookstore.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BookDescription")
@Getter
@Setter
public class BookDescription {
    @Id
    private String _id;

    private Long bookId;

    private String description;

    public BookDescription(Long bookId, String description) {
        this.bookId = bookId;
        this.description = description;
    }

    public BookDescription() {

    }
}
