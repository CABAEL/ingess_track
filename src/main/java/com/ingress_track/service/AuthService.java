package com.ingress_track.service;

import com.ingress_track.dto.AuthRequestDto;
import com.ingress_track.dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto AuthenticateUser(AuthRequestDto authRequestDto);
}
