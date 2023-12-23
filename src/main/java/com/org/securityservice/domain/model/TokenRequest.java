package com.org.securityservice.domain.model;

import lombok.Data;

import java.util.Map;

@Data
public class TokenRequest {

    private String username;

    private Map<String, Object> claims;

    public void addClaim(String claim, String value){
        this.claims.put(claim, value);
    }

}