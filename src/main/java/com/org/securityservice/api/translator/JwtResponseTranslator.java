package com.org.securityservice.api.translator;

import com.org.securityservice.api.model.JwtResponseModel;
import com.org.securityservice.domain.model.JwtResponse;
import org.springframework.stereotype.Component;

@Component
public class JwtResponseTranslator extends Translator<JwtResponseModel, JwtResponse>{

    @Override
    public JwtResponseModel toApiModel(JwtResponse domain) {
        JwtResponseModel jwtResponseModel = new JwtResponseModel();
        jwtResponseModel.setToken(domain.getToken());
        return jwtResponseModel;
    }
}
