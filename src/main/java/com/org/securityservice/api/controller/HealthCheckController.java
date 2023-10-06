package com.org.securityservice.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity getHealth(){
        return ResponseEntity.ok("healthy");
    }

}
