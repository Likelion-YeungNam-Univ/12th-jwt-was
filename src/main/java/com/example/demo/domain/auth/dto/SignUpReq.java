package com.example.demo.domain.auth.dto;


import com.example.demo.domain.user.domain.User;

public record SignUpReq(
        String username,
        String nickName,
        String email,
        String password
) {
    public User toEntity() {
        return User.builder()
                .username(username)
                .nickName(nickName)
                .email(email)
                .password(password)
                .build();
    }
}
