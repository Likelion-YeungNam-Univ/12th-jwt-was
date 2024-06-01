package com.example.demo.domain.auth.dto;

public record SignInReq(
        String username,
        String password
) {
}
