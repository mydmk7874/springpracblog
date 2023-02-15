package com.sparta.springpracblog.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

//@Embeddable
@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[a-z1-9]{4,10}")
    @Column(unique = true)
    private String username;

    @Pattern(regexp = "[a-zA-Z1-9]{8,15}")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}