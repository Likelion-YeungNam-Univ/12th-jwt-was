package com.example.demo.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenRes(
        @Schema(description = "액세스 토큰", example = "...")
        String accessToken,
        @Schema(description = "리프레시 토큰", example = "...")
        String refreshToken
) {
}
