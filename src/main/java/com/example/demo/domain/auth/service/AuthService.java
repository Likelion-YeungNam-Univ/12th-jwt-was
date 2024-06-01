package com.example.demo.domain.auth.service;

import com.example.demo.domain.auth.dto.SignInReq;
import com.example.demo.domain.auth.dto.SignUpReq;
import com.example.demo.domain.auth.dto.TokenRes;
import com.example.demo.domain.user.domain.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.auth.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenRes signIn(SignInReq request){
        User user = checkUserValid(request);

        return issueToken(user);
    }

    public User signUp(SignUpReq request){
        User user = checkUserAvailable(request);

        return user;
    }

    private User readUserOrThrow(String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(
            () -> new IllegalArgumentException("존재하지 않는 계정입니다.")
        );

        return user;
    }

    private User checkUserValid(SignInReq request){
        User user = readUserOrThrow(request.username());

        if(user.getPassword() != request.password()){
            log.error("비밀번호가 일치하지 않습니다.");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    private User checkUserAvailable(SignUpReq request){
        if(userRepository.existsByUsername(request.username())){
            log.error("이미 존재하는 계정입니다.");
            throw new IllegalArgumentException("이미 존재하는 계정입니다.");
        }
        log.info("{} 계정 생성",request.username());
        return userRepository.save(request.toEntity());
    }


    private TokenRes issueToken(User user){
        return new TokenRes(
                jwtTokenProvider.issueAccessToken(user.getUsername()),
                jwtTokenProvider.issueRefreshToken(user.getUsername()));
    }
}
