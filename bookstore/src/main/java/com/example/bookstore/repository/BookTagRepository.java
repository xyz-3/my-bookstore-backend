package com.example.bookstore.repository;

import com.example.bookstore.entity.BookTag;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface BookTagRepository extends Neo4jRepository<BookTag, Long> {
    List<BookTag> findBookTagsByTag(String tag);

//    @Query("MATCH (a:BookTag)-[:relatedBooks]->(b) " +
//            "WHERE a.tag = $tag " +
//            "RETURN b "
//    )
//    List<BookTag> findRelatedBooksByTag(@Param("tag") String tag);
//
//    @Query("MATCH (a:BookTag)-[:relatedBooks*..2]->(b:BookTag) WHERE a.tag=${tag} RETURN DISTINCT b")
//    List<BookTag> findRelatedRelatedBooksByTag(@Param("tag") String tag);
}
