package com.ingress_track.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponseDto {

    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String role;

}
