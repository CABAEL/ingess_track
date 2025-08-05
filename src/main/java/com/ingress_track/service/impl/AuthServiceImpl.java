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

import java.time.Instant;
import java.util.Map;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private UserCredentialRepository userCredentialRepository;
    private SecurityConfig securityConfig;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDto AuthenticateUser(AuthRequestDto authRequestDto , String clientIp, String userAgent){


        String username = authRequestDto.getUsername();

        UserCredential userCredential = userCredentialRepository.findByUserName(username)
                .orElse(null);

        if (userCredential == null ||
                !securityConfig.passwordEncoder().matches(authRequestDto.getPassword(), userCredential.getPassWord())) {
            throw new UnauthorizedException("Invalid credentials!");
        }

        Map<String, Object> claims = Map.of(
                "iat", Instant.now().getEpochSecond(),
                "role", String.valueOf(userCredential.getRole()),
                "userId", userCredential.getUserId(),
                "client_ip",clientIp,
                "user_agent",userAgent
        );

        System.out.println(claims);


        String token = jwtUtil.generateToken(username, claims);

        AuthResponseDto response = new AuthResponseDto();
        response.setUsername(userCredential.getUserName());
        response.setAccessToken(token);
        response.setRole(String.valueOf(userCredential.getRole()));

        return response;

    }

}
