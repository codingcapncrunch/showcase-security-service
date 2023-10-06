package com.org.securityservice.domain.service.token;

import com.org.securityservice.domain.model.TokenRequest;

public interface TokenService {

    String generateToken(TokenRequest tokenRequest);

    boolean isExpiredToken(String token);

}
