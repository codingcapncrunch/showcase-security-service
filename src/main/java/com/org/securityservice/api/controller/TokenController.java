package com.org.securityservice.api.controller;

import com.org.securityservice.api.model.TokenRequest;
import com.org.securityservice.api.translator.TokenTranslator;
import com.org.securityservice.domain.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
public class TokenController {

    private TokenTranslator tokenTranslator;
    private TokenService tokenService;

    @Autowired
    public TokenController(TokenTranslator tokenTranslator, TokenService tokenService) {
        this.tokenTranslator = tokenTranslator;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity tokenRequest(@RequestBody TokenRequest tokenRequest){

        String token = this.tokenService.generateToken(this.tokenTranslator.toDomainModel(tokenRequest));

        return ResponseEntity.ok(token);
    }


}
