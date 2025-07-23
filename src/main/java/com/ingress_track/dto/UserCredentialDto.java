package com.ingress_track.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class UserCredentialDto {

    @NotBlank
    private Long user_id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
