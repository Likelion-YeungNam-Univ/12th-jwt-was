package com.example.demo.domain.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickName;
    private String email;


    @Builder
    public User(Long id, String username, String password, String nickName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
    }
}