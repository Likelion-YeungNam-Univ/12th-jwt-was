package com.example.demo.domain.auth.controller;


import com.example.demo.domain.auth.dto.SignInReq;
import com.example.demo.domain.auth.dto.SignUpReq;
import com.example.demo.domain.auth.dto.TokenRes;
import com.example.demo.domain.auth.service.AuthService;
import com.example.demo.domain.user.domain.User;
import com.example.demo.global.security.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    @Operation(summary = "로그인", description = "로그인을 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenRes.class)))
    })
    public ResponseEntity<?> siginIn(@RequestBody SignInReq request) {

        return ResponseEntity.ok(authService.signIn(request));
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입을 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    public ResponseEntity<?> signUp(@RequestBody SignUpReq request) {

        return ResponseEntity.ok(authService.signUp(request));
    }

    @GetMapping("/verify")
    @Operation(summary = "로그인된 사용자 확인", description = "현재 로그인되어있는 사용자의 username을 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "username")))
    })
    public ResponseEntity<?> verify(@AuthenticationPrincipal CustomUserDetails userDetails){
        log.warn("userDetails : {}", userDetails.getUsername());

        return ResponseEntity.ok(userDetails.getUsername());
    }
}
