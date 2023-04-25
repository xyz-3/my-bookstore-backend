package com.example.bookstore.dao;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.RegisterForm;

import java.util.Optional;
import java.util.Set;

public interface UserDao {

    Optional<User> findUserByUsername(String username);

//    User getUserByUsername(String username);
//
    User getUserById(Long id);

    User addUser(RegisterForm registerForm);

//    void updateUser(User user);

    Optional<User> findUserByEmail(String email);

//    User checkUser(String username, String password);

//    Set<User> getAllUsers();

    User changeInfo(Integer id, RegisterForm registerForm);

}
