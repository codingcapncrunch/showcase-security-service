package com.org.securityservice.api.translator;

import com.org.securityservice.api.model.AuthenticateRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateRequestTranslator extends Translator<AuthenticateRequest, com.org.securityservice.domain.model.AuthenticateRequest>{

    @Override
    public AuthenticateRequest toApiModel(com.org.securityservice.domain.model.AuthenticateRequest domain) {
        AuthenticateRequest authenticateRequest = new AuthenticateRequest();
        authenticateRequest.setEmail(domain.getEmail());
        authenticateRequest.setPassword(domain.getPassword());
        return authenticateRequest;
    }

    @Override
    public com.org.securityservice.domain.model.AuthenticateRequest toDomainModel(AuthenticateRequest api) {
        com.org.securityservice.domain.model.AuthenticateRequest authenticateRequest = new com.org.securityservice.domain.model.AuthenticateRequest();
        authenticateRequest.setEmail(api.getEmail());
        authenticateRequest.setPassword(api.getPassword());
        return authenticateRequest;
    }
}
