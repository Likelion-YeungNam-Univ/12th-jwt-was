package com.example.demo.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInReq(
        @Schema(description = "사용자 아이디", example = "test", required = true)
        String username,
        @Schema(description = "비밀번호", example = "test", required = true)
        String password
) {
}
