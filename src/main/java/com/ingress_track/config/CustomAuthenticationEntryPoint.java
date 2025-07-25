package com.ingress_track.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingress_track.dto.ApiErrorResponse;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, java.io.IOException {

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of("Invalid or missing credentials")
        );


        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}