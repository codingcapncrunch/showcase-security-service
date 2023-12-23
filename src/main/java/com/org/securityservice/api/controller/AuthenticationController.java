package com.org.securityservice.api.controller;

import com.org.securityservice.api.model.AuthenticateRequest;
import com.org.securityservice.api.translator.AuthenticateRequestTranslator;
import com.org.securityservice.api.translator.AuthenticationResponseTranslator;
import com.org.securityservice.domain.model.AuthenticationResponse;
import com.org.securityservice.domain.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    private AuthenticationResponseTranslator authenticationResponseTranslator;
    private AuthenticateRequestTranslator authenticateRequestTranslator;
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationResponseTranslator authenticationResponseTranslator, AuthenticateRequestTranslator authenticateRequestTranslator, AuthenticationService authenticationService) {
        this.authenticationResponseTranslator = authenticationResponseTranslator;
        this.authenticateRequestTranslator = authenticateRequestTranslator;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity signup(@RequestBody AuthenticateRequest request){
        AuthenticationResponse response = this.authenticationService.authenticate(this.authenticateRequestTranslator.toDomainModel(request));
        return ResponseEntity.ok(this.authenticationResponseTranslator.toApiModel(response));
    }

}
