package com.org.securityservice.domain.model;

import lombok.Data;

@Data
public class JwtRequest {

    private static final long serialVersionUID = 2636936156391265891L;
    private String username;
    private String password;

}
