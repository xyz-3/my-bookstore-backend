package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.request.RegisterForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
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

    @RequestMapping(value = "/User/ChangeInfo/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public User ChangeInfo(@PathVariable("id") Integer id, @RequestBody @NotNull RegisterForm registerForm) {
        System.out.println("id: " + id);
        System.out.println(registerForm);
        return userService.changeInfo(id, registerForm);
    }

}
