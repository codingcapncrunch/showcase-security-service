package com.org.securityservice.domain.service.login;

import com.org.securityservice.domain.model.JwtRequest;
import com.org.securityservice.domain.model.JwtResponse;

public interface LoginService {

    JwtResponse login(JwtRequest jwtRequest);

}
