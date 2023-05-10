package com.example.bookstore.util.response;

import com.example.bookstore.entity.User;

public class UserInfoForm {
    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String notes;

    private int role;

    public UserInfoForm(Long id, String username, String avatar, String email, String notes){
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.notes = notes;
    }

    public UserInfoForm(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
//        this.notes = user.getNotes();
        this.role = user.getRole();
    }

    public void setRole(int role){
        this.role = role;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }


    public int getRole(){
        return this.role;
    }
    public Long getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getAvatar(){
        return this.avatar;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNotes(){
        return this.notes;
    }

    public UserInfoForm() {}
}
