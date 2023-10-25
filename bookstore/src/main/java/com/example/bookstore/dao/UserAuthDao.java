package com.example.bookstore.dao;

import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;

public interface UserAuthDao {

    UserAuth getUserAuthByUser(User user);

    UserAuth addUserAuth(User user, String password);
}
