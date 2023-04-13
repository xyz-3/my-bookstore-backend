package com.example.bookstore.service.impl;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.util.request.RegisterForm;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByUserName(String username) {
        return userDao.findUserByUsername(username).orElseThrow();
    }

    @Override
    public String handleRegister(RegisterForm registerForm) {
        if (userDao.findUserByUsername(registerForm.getUsername()).isPresent())
            return "account already exists";
        if (userDao.findUserByEmail(registerForm.getEmail()).isPresent())
            return "email address already exists";
        userDao.addUser(registerForm);
        return "OK";
    }

    @Override
    public String handleLogin(RegisterForm registerForm) {
        if (userDao.findUserByUsername(registerForm.getUsername()).isEmpty())
            return "account does not exist";
        if(!userDao.findUserByUsername(registerForm.getUsername()).get().getPassword().equals(registerForm.getPassword()))
            return "password is incorrect";
        return "OK";
    }

}
