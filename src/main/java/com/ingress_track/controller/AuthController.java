package com.ingress_track.controller;


import com.ingress_track.dto.AuthRequestDto;
import com.ingress_track.dto.AuthResponseDto;
import com.ingress_track.service.AuthService;
import com.ingress_track.util.IpAddressUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/user")

public class AuthController {

    private AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> requestAuth(@Valid @RequestBody AuthRequestDto authRequestDto, HttpServletRequest request){

        String clientIp = IpAddressUtil.getClientIp(request);
        String userAgent = request.getHeader("user-agent");

        AuthResponseDto authResponseDto = authService.AuthenticateUser(authRequestDto , clientIp, userAgent);
        return ResponseEntity.status(200).body(authResponseDto);

    }

}
