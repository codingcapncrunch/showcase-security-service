package com.org.securityservice.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public String hello(){
        return "you got access to secure";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/secure/admin")
    public String adminHello(){
        return "you got access to secure admin - you must have the ROLE_ADMIN authority";
    }

}
