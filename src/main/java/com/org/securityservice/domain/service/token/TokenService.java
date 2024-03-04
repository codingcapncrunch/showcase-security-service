package com.org.securityservice.domain.service.token;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    public String generateJwtToken(UserDetails userDetails);

    public Boolean validateJwtToken(String token, UserDetails userDetails);

    public String getUsernameFromToken(String token);

}
