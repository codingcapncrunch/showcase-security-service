package com.org.securityservice.api.translator;

import com.org.securityservice.api.model.JwtRequestModel;
import com.org.securityservice.domain.model.JwtRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtRequestTranslator extends Translator<JwtRequestModel, JwtRequest>{

    @Override
    public JwtRequest toDomainModel(JwtRequestModel api) {
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername(api.getUsername());
        jwtRequest.setPassword(api.getPassword());
        return jwtRequest;
    }
}
