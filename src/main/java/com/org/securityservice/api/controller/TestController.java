package com.org.securityservice.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/secure")
public class TestController {

    @GetMapping
    public ResponseEntity test(){
        return ResponseEntity.ok("should be secure");
    }

}
