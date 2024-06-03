package com.example.demo.domain.auth.controller;


import com.example.demo.domain.auth.dto.SignInReq;
import com.example.demo.domain.auth.dto.SignUpReq;
import com.example.demo.domain.auth.service.AuthService;
import com.example.demo.global.security.userdetails.CustomUserDetails;
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
    public ResponseEntity<?> siginIn(@RequestBody SignInReq request) {

        return ResponseEntity.ok(authService.signIn(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpReq request) {

        return ResponseEntity.ok(authService.signUp(request));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@AuthenticationPrincipal CustomUserDetails userDetails){
        log.warn("userDetails : {}", userDetails.getUsername().toString());

        return ResponseEntity.ok("반갑습니다! " + userDetails.getUsername() + "님!");
    }
}
