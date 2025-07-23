package com.ingress_track.service.impl;

import com.ingress_track.config.SecurityConfig;
import com.ingress_track.dto.AuthRequestDto;
import com.ingress_track.dto.AuthResponseDto;
import com.ingress_track.repository.UserCredentialRepository;
import com.ingress_track.repository.UserRepository;
import com.ingress_track.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private UserCredentialRepository userCredentialRepository;
    private SecurityConfig securityConfig;


    @Override
    public AuthResponseDto AuthenticateUser(AuthRequestDto authRequestDto){


        //TODO match user data request to DB user data
        String username = authRequestDto.getUsername();
        String password = securityConfig.passwordEncoder().encode(authRequestDto.getPassword());



        AuthResponseDto response = new AuthResponseDto();
        response.setAccessToken("sample.jwt.token.here");
        response.setUsername(password);
        response.setRole("USER");

        return response;
    }

}
