package com.ingress_track.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "your-256-bit-secret-key-change-this-please!!";
    private static final long EXPIRATION_TIME = 1000 * 60 * 30; // 30 minutes in milliseconds


    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate token
    public static String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate and parse token
    public static Jws<Claims> validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    // Extract claims
    public static Claims getClaims(String token) throws JwtException {
        return validateToken(token).getBody();
    }

    // Check if token is expired
    public static boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
