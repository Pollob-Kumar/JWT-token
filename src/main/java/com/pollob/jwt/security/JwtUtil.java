package com.pollob.jwt.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // এই Secret Key দিয়ে token বানাবো
    private String secret = "ami-ekta-secret-key-123456789-abcdefghijk";

    // Token বানাও
    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email)           // token এর ভেতরে email রাখলাম
                .setIssuedAt(new Date())      // কখন বানানো হলো
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // ১৫ মিনট
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))  // secret দিয়ে sign
                .compact();
    }

    // Token থেকে email বের করো
    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();   // email return করবে
    }
}