package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;
import com.example.bookstore.util.request.LoginForm;
import com.example.bookstore.util.request.RegisterForm;
import jdk.jfr.consumer.RecordingFile;

public interface UserService {

    User getUserByUserName(String username);

    Boolean checkUserExist(String username);

    Boolean checkUserExistByEmail(String email);

    User handleRegister(RegisterForm registerForm);

//    User handleLogin(RegisterForm registerForm);
    UserAuth handleLogin(LoginForm loginForm);

//    User checkUser(RegisterForm registerForm);

//    UserAuth checkUser(String username, String password);

    User changeInfo(Integer id, RegisterForm registerForm);

    User getUserById(Long id);
}
