package com.example.demo.domain.auth.dto;


import com.example.demo.domain.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpReq(
        @Schema(description = "사용자 아이디", example = "test", required = true)
        String username,
        @Schema(description = "닉네임", example = "test", required = true)
        String nickName,
        @Schema(description = "이메일", example = "test@gmail.com", required = true)
        String email,
        @Schema(description = "비밀번호", example = "test", required = true)
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
