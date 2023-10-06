package com.org.securityservice.api.model;

import lombok.Data;

import java.util.Map;

@Data
public class TokenRequest {

    private String username;

    private Map<String, Object> claims;

}
