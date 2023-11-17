package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
@Getter
@Setter
public class BookTag {
    @Id
    @GeneratedValue
    private Long id;

    private String tag;

    private List<Long> bookIds;

    @Relationship(type = "relatedBooks")
    public Set<BookTag> relatedTags;

    @JsonBackReference
    public Set<BookTag> getRelatedTags() {
        return relatedTags;
    }

    @JsonBackReference
    public void setRelatedTags(Set<BookTag> relatedTags) {
        this.relatedTags = relatedTags;
    }

    public BookTag(){
        relatedTags = new HashSet<>();
        bookIds = new ArrayList<>();
    }

    public BookTag(String tag) {
        this.tag = tag;
        relatedTags = new HashSet<>();
        bookIds = new ArrayList<>();
    }

    public void addBookId(Long id){
        this.bookIds.add(id);
    }

    public void addRelatedTag(BookTag tag){
        this.relatedTags.add(tag);
    }
}
