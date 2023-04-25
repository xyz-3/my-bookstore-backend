package com.example.bookstore.util.request;

public class RegisterForm {
    private String username;
    private String password;
    private String email;
    private int role;
    private String avatar;
    private String notes;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public String getNotes() {
        return notes;
    }

    public String getAvatar() {
        return avatar;
    }
}
