package com.ingress_track.service.impl;

import com.ingress_track.config.SecurityConfig;
import com.ingress_track.dto.AuthRequestDto;
import com.ingress_track.dto.AuthResponseDto;
import com.ingress_track.entity.UserCredential;
import com.ingress_track.exception.UnauthorizedException;
import com.ingress_track.repository.UserCredentialRepository;
import com.ingress_track.repository.UserRepository;
import com.ingress_track.service.AuthService;
import com.ingress_track.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private UserCredentialRepository userCredentialRepository;
    private SecurityConfig securityConfig;


    @Override
    public AuthResponseDto AuthenticateUser(AuthRequestDto authRequestDto){

        String username = authRequestDto.getUsername();

        UserCredential userCredential = userCredentialRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + authRequestDto.getUsername()));

        if (!securityConfig.passwordEncoder().matches(authRequestDto.getPassword(), userCredential.getPassWord())) {
            throw new UnauthorizedException("Invalid credentials!");
        }

        Map<String, Object> claims = Map.of(
                "role", String.valueOf(userCredential.getRole()),  // optionally dynamic later
                "userId", userCredential.getUserId()
        );

        String token = JwtUtil.generateToken(username, claims);

        AuthResponseDto response = new AuthResponseDto();
        response.setUsername(userCredential.getUserName());
        response.setAccessToken(token);
        response.setRole(String.valueOf(userCredential.getRole()));

        return response;

    }

}
