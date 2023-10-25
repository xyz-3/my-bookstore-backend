package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.UserDao;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.request.RegisterForm;
import org.springframework.stereotype.Repository;
import com.example.bookstore.entity.User;

import com.example.bookstore.constant.Constant;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(RegisterForm registerForm) {
        User user = new User();
        user.setUsername(registerForm.getUsername());
//        user.setPassword(registerForm.getPassword());
        user.setEmail(registerForm.getEmail());
        user.setRole(registerForm.getRole());
        user.setAvatar(Objects.equals(registerForm.getAvatar(), "") ? Constant.default_avatar : registerForm.getAvatar());
        System.out.println(registerForm.getNotes());
        user.setNotes(Objects.equals(registerForm.getNotes(), "") ? Constant.default_notes : registerForm.getNotes());
        System.out.println(registerForm.getNotes());
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User changeInfo(Integer id, RegisterForm registerForm){
        User user = userRepository.findById(id);
        user.setUsername(registerForm.getUsername());
        user.setEmail(registerForm.getEmail());
        user.setRole(registerForm.getRole());
        user.setAvatar(Objects.equals(registerForm.getAvatar(), "") ? Constant.default_avatar : registerForm.getAvatar());
        user.setNotes(Objects.equals(registerForm.getNotes(), "") ? Constant.default_notes : registerForm.getNotes());
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }


    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public List<User> setUserStatus(Long id, boolean blocked){
        User user = userRepository.findById(id).get();
        if(user == null) return null;
        user.setBlocked(blocked);
        userRepository.save(user);
        return userRepository.findAll();
    }


}
