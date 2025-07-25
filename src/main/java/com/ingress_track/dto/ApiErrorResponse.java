package com.ingress_track.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApiErrorResponse {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private List<String> errors;
}
