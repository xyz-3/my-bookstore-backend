package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.RegisterForm;

public interface UserService {

    User getUserByUserName(String username);

    String handleRegister(RegisterForm registerForm);

    String handleLogin(RegisterForm registerForm);

}
