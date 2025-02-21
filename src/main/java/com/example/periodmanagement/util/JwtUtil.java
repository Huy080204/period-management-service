package com.example.periodmanagement.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    private static String SIGNER_KEY;

    @Value("${jwt.signerKey}")
    public void setSignerKey(String signerKey) {
        SIGNER_KEY = signerKey;
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNER_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> extractAuthorities(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SIGNER_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (List<String>) claims.get("authorities", List.class);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SIGNER_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
