package com.org.securityservice.api.controller;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import io.jsonwebtoken.Jwts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/token")
public class TokenController {

    @Value("#{new Integer('${jwtExpirationInMs}')}")
    private Integer jwtExpirationInMs;

    @Value("{jwtSecret}")
    private String jwtSecret;

    @GetMapping
    public ResponseEntity tokenRequest(@RequestParam(name = "val") String userName){

        Map<String, Object> claims = new HashMap<>();
        claims.put("claim1", "test1");
        claims.put("claim2", "test2");

        return ResponseEntity.ok(this.generateToken(claims, userName));
    }


    private String generateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 360000))
                .signWith(SignatureAlgorithm.HS512, "rogerdogerplanthatboat").compact();
    }

}
