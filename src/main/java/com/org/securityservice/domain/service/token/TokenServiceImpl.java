package com.org.securityservice.domain.service.token;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import com.org.securityservice.domain.model.TokenRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("#{new Integer('${jwtExpirationInMs}')}")
    private Integer jwtExpirationInMs;


    @Override
    public String generateToken(TokenRequest tokenRequest) {

        return this.generateToken(tokenRequest.getClaims(), tokenRequest.getUsername());
    }

    @Override
    public boolean isExpiredToken(String token) {
        return false;
    }

    private String generateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, this.getSigningKey()).compact();
    }

    private Key getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

}
