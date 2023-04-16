package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.RegisterForm;
import jdk.jfr.consumer.RecordingFile;

public interface UserService {

    User getUserByUserName(String username);

    Boolean checkUserExist(String username);

    Boolean checkUserExistByEmail(String email);

    User handleRegister(RegisterForm registerForm);

    User handleLogin(RegisterForm registerForm);

    User checkUser(RegisterForm registerForm);


}
