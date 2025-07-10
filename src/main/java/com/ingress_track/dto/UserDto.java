package com.ingress_track.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int userStatus;
    private int userType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
