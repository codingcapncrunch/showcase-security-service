package com.org.securityservice.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String token;

}
