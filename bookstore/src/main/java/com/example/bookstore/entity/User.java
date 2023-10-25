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
@JsonIgnoreProperties(value = {"userAuth", "orders", "cart"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 64, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 64, nullable = false, unique = true)
    private String email;

    @Column(name = "avatar", length = 10240, nullable = true)
    private String avatar;

    @Column(name = "role", nullable = false)
    private int role;

    @Column(name = "blocked", nullable = false)
    private boolean blocked;

    @Column(name = "notes" , length = 10240, nullable = true)
    private String notes;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private UserAuth userAuth;

    @JsonIgnoreProperties(value = {"user"})
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "adder" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private List<CartItem> cart;


    public User(String username, String email, String avatar, int role) {
        this.username = username;
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
