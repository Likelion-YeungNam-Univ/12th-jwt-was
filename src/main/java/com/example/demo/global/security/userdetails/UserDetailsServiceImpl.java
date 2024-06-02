package com.example.demo.global.security.userdetails;

import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username){
        return new CustomUserDetails(
                userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException(username)));
    }

    public CustomUserDetails loadUserById(final Long userId) {
        return new CustomUserDetails(
                userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(userId.toString())));

    }

}
