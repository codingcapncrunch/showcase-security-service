package com.org.securityservice.domain.service.token;

import com.org.securityservice.domain.model.TokenRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
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
        //TODO
        return false;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        //TODO
        return true;
    }

    @Override
    public String extractUserName(String token) {
        //TODO
        return null;
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
