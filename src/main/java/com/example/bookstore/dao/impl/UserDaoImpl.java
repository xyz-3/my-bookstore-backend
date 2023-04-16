package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.UserDao;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.request.RegisterForm;
import org.springframework.stereotype.Repository;
import com.example.bookstore.entity.User;

import com.example.bookstore.constant.Constant;

import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(RegisterForm registerForm) {
        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());
        user.setEmail(registerForm.getEmail());
        user.setRole(registerForm.getRole());
        user.setAvatar(Objects.equals(registerForm.getAvatar(), "") ? Constant.default_avatar : registerForm.getAvatar());
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User checkUser(String username, String password){
        return userRepository.checkUser(username, password);
    }
}
