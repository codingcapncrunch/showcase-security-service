package com.org.securityservice.domain.service.token;

import com.org.securityservice.domain.model.TokenRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String generateToken(TokenRequest tokenRequest);

    boolean isExpiredToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String extractUserName(String token);

}
