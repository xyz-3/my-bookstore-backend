package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 64, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "email", length = 64, nullable = false, unique = true)
    private String email;

    @Column(name = "avatar", length = 10240, nullable = true)
    private String avatar;

    @Column(name = "role", nullable = false)
    private int role;
//
//    @Column(name = "registertime", length = 64, nullable = false)
//    private Date registerTime;

    @JsonIgnoreProperties(value = {"user", "goodsList"})
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Goods> goodsList;


//    public User(String username, String password, String email, String role, Date registerTime) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.role = role;
//        this.avatar = "";
//        this.registerTime = registerTime;
//    }
    public User(String username, String password, String email, String avatar, int role) {
        this.username = username;
        this.password = password;
        this.email = email;
        if(avatar == null){
            this.avatar = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.veryicon.com%2Ficons%2Finternet--web%2Fprejudice%2Fuser-128.html&psig=AOvVaw0qEA8PeNe__lU0QefEoNxl&ust=1681628820335000&source=images&cd=vfe&ved=0CA4QjRxqFwoTCLDOudupq_4CFQAAAAAdAAAAABAD";
        }else{
            this.avatar = avatar;
        }
        this.role = role;
    }

    public User() {

    }
}
