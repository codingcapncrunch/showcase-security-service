package com.org.securityservice.domain.service.authentication;


import com.org.securityservice.domain.model.AuthenticateRequest;
import com.org.securityservice.domain.model.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticateRequest request);

}
