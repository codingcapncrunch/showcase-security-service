package com.org.securityservice.domain.service.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserDetailsService {

    UserDetails loadUserByUsername(String username);
}
