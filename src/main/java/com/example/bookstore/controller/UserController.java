package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //get user info
    @RequestMapping(value = "/user/{UserName}", method = RequestMethod.GET)
    public User getUserByAccount(@PathVariable("UserName") String UserName) {
        return userService.getUserByUserName(UserName);
    }

}
