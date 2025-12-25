package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenProvider {

    // 🔐 Secret key used to sign JWT
    private final String secret;

    // ⏱ Token validity time (milliseconds)
    private final long validity;

    // ✅ Constructor injection (as per constraint)
    public JwtTokenProvider(String secret, long validity) {
        this.secret = secret;
        this.validity = validity;
    }

    // 🔑 Generate JWT Token
    public String generateToken(Long userId, String email, String role) {

        return Jwts.builder()
                // subject = userId
                .setSubject(String.valueOf(userId))

                // custom payload data
                .claim("email", email)
                .claim("role", role)

                // issued time
                .setIssuedAt(new Date())

                // expiry time
                .setExpiration(new Date(System.currentTimeMillis() + validity))

                // signing algorithm + secret
                .signWith(SignatureAlgorithm.HS256, secret)

                // final token string
                .compact();
    }

    // ✅ Validate token (used during authentication)
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 📦 Extract all claims from token
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    // 📧 Extract email from token (USED IN AUTH FLOW)
    public String getEmailFromToken(String token) {
        return getClaims(token).get("email", String.class);
    }

    // 🧑 Extract userId from token
    public Long getUserIdFromToken(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    // 🎭 Extract role from token
    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }
}
