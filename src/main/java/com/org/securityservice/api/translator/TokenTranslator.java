package com.org.securityservice.api.translator;

import com.org.securityservice.api.model.TokenRequest;
import org.springframework.stereotype.Component;

@Component
public class TokenTranslator extends Translator<TokenRequest, com.org.securityservice.domain.model.TokenRequest>{

    @Override
    public TokenRequest toApiModel(com.org.securityservice.domain.model.TokenRequest domain) {

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setUsername(domain.getUsername());
        tokenRequest.setClaims(domain.getClaims());
        return tokenRequest;
    }

    @Override
    public com.org.securityservice.domain.model.TokenRequest toDomainModel(TokenRequest api) {

        com.org.securityservice.domain.model.TokenRequest tokenRequest = new com.org.securityservice.domain.model.TokenRequest();
        tokenRequest.setUsername(api.getUsername());
        tokenRequest.setClaims(api.getClaims());
        return tokenRequest;
    }
}
