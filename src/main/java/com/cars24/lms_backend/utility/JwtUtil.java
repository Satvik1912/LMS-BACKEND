package com.cars24.lms_backend.utility;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "CHANGE_THIS_TO_A_32_CHAR_LONG_SECRET";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key) // ✅ Correct method for JJWT 0.12.6
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser() // ✅ FIXED: Use correct JJWT 0.12.6 API
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
            return !claims.getPayload().getExpiration().before(new Date());
        } catch (JwtException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
        }
        return false;
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key) // ✅ FIXED: Correct method for verification
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
