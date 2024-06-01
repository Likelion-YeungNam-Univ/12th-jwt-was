package com.example.demo.domain.auth.dto;

public record TokenRes(
        String accessToken,
        String refreshToken
) {
}
