package com.ingress_track.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Long id;

    @NotNull(message = "First name is mandatory")
    @Size(min = 2, max = 50)
    private String firstName;

    @Nullable
    @Size(min = 2, max = 50)
    private String middleName;

    @NotNull(message = "Last name is mandatory")
    @Size(min = 2, max = 50)
    private String lastName;

    private int userStatus = 1;
    private int userType = 1;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
