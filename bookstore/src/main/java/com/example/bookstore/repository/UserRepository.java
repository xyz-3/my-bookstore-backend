package com.example.bookstore.repository;

import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    User findById(Integer id);

//    @Query(value = "from User where username = :username and password = :password")
//    User checkUser(@Param("username") String username, @Param("password") String password);

}
