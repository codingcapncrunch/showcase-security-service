package com.org.securityservice.api.controller;

import com.org.securityservice.api.model.JwtRequestModel;
import com.org.securityservice.api.translator.JwtRequestTranslator;
import com.org.securityservice.api.translator.JwtResponseTranslator;
import com.org.securityservice.domain.model.JwtResponse;
import com.org.securityservice.domain.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginService loginService;
    private JwtRequestTranslator jwtRequestTranslator;
    private JwtResponseTranslator jwtResponseTranslator;

    @Autowired
    public LoginController(LoginService loginService, JwtRequestTranslator jwtRequestTranslator, JwtResponseTranslator jwtResponseTranslator) {
        this.loginService = loginService;
        this.jwtRequestTranslator = jwtRequestTranslator;
        this.jwtResponseTranslator = jwtResponseTranslator;
    }

    @PostMapping("/login")
    public ResponseEntity createToken(@RequestBody JwtRequestModel request) {

        JwtResponse jwtResponse = this.loginService.login(this.jwtRequestTranslator.toDomainModel(request));

        return ResponseEntity.ok(this.jwtResponseTranslator.toApiModel(jwtResponse));
    }


}
