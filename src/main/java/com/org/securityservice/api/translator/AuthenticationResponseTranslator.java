package com.org.securityservice.api.translator;

import com.org.securityservice.api.model.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationResponseTranslator extends Translator<AuthenticationResponse, com.org.securityservice.domain.model.AuthenticationResponse>{

    @Override
    public AuthenticationResponse toApiModel(com.org.securityservice.domain.model.AuthenticationResponse domain) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(domain.getToken());
        return authenticationResponse;
    }

    @Override
    public com.org.securityservice.domain.model.AuthenticationResponse toDomainModel(AuthenticationResponse api) {
        com.org.securityservice.domain.model.AuthenticationResponse authenticationResponse = new com.org.securityservice.domain.model.AuthenticationResponse();
        authenticationResponse.setToken(api.getToken());
        return authenticationResponse;
    }
}
