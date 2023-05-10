package com.example.bookstore.service.impl;

import com.example.bookstore.dao.UserAuthDao;
import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;
import com.example.bookstore.service.UserService;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.util.request.LoginForm;
import com.example.bookstore.util.request.RegisterForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserAuthDao userAuthDao;

    public UserServiceImpl(UserDao userDao, UserAuthDao userAuthDao) {
        this.userDao = userDao;
        this.userAuthDao = userAuthDao;
    }

    @Override
    public User getUserByUserName(String username) {
        return userDao.findUserByUsername(username).get();
    }

    @Override
    public User handleRegister(RegisterForm registerForm) {
        User user = userDao.addUser(registerForm);
        userAuthDao.addUserAuth(user, registerForm.getPassword());
        return user;
    }
    @Override
    public UserAuth handleLogin(LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if(userDao.findUserByUsername(username).isEmpty()){
            return null;
        }
        User user = userDao.findUserByUsername(username).get();
        UserAuth userAuth = userAuthDao.getUserAuthByUser(user);
        if(userAuth.getPassword().equals(password)){
            return userAuth;
        }else{
            return null;
        }
    }

//    @Override
//    public User checkUser(RegisterForm registerForm){
//        return userDao.checkUser(registerForm.getUsername(), registerForm.getPassword());
//    }
//    @Override
//    public UserAuth checkUser(String username, String password){
//        return userDao.checkUser(username, password);
//    }

    @Override
    public Boolean checkUserExist(String username){
        return userDao.findUserByUsername(username).isPresent();
    }

    @Override
    public Boolean checkUserExistByEmail(String email){
        return userDao.findUserByEmail(email).isPresent();
    }

    @Override
    public User changeInfo(Integer id, RegisterForm registerForm){
        return userDao.changeInfo(id, registerForm);
    }

    @Override
    public User getUserById(Long id){
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
