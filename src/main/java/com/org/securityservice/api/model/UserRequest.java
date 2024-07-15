package com.org.securityservice.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {

    @NotBlank(message = "username cannot be blank")
    private String username;

}
