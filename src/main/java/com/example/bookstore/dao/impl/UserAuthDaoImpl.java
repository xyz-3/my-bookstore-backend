package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.UserAuthDao;
import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;
import com.example.bookstore.repository.UserAuthRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {
    private final UserAuthRepository userAuthRepository;

    public UserAuthDaoImpl(UserAuthRepository userAuthRepository){
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserAuth getUserAuthByUser(User user){
        return userAuthRepository.getUserAuthByUser(user);
    }

    @Override
    public UserAuth addUserAuth(User user, String password){
        UserAuth userAuth = new UserAuth();
        userAuth.setUser(user);
        userAuth.setPassword(password);
        userAuthRepository.save(userAuth);
        return userAuth;
    }
}
