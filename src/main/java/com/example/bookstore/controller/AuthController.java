package com.example.bookstore.controller;

import com.example.bookstore.service.UserService;
import com.example.bookstore.util.Message;
import com.example.bookstore.util.request.RegisterForm;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Message Login(@RequestBody @NotNull RegisterForm registerForm) {
        return new Message(userService.handleLogin(registerForm));
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Message Register(@RequestBody @NotNull RegisterForm registerForm) {
        return new Message(userService.handleRegister(registerForm));
    }

    @RequestMapping(value = "/checkSession", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Message checkSession(){
        return new Message("OK");
    }
}
