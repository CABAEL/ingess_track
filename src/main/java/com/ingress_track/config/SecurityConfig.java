package com.ingress_track.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs (optional but common)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/user/authenticate").permitAll() // allow unauthenticated access
                        .anyRequest().authenticated() // all other endpoints require login
                ).exceptionHandling(ex -> ex.authenticationEntryPoint(customAuthenticationEntryPoint)
                );

        return http.build();
    }
}