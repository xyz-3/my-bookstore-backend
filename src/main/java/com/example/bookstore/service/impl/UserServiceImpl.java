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
        return userDao.findUserByUsername(username).get();
    }

    @Override
    public User handleRegister(RegisterForm registerForm) {
        return userDao.addUser(registerForm);
    }

    @Override
    public User handleLogin(RegisterForm registerForm) {
        if (userDao.findUserByUsername(registerForm.getUsername()) == null)
            return null;
        if(!userDao.findUserByUsername(registerForm.getUsername()).get().getPassword().equals(registerForm.getPassword())) {
            return null;
        }
        User user = userDao.findUserByUsername(registerForm.getUsername()).get();
        System.out.println(user);
        return user;
    }

    @Override
    public User checkUser(RegisterForm registerForm){
        return userDao.checkUser(registerForm.getUsername(), registerForm.getPassword());
    }

    @Override
    public Boolean checkUserExist(String username){
        return userDao.findUserByUsername(username).isPresent();
    }

    @Override
    public Boolean checkUserExistByEmail(String email){
        return userDao.findUserByEmail(email).isPresent();
    }
}
