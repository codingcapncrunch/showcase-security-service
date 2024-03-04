package com.org.securityservice.api.controller;

import com.org.securityservice.api.model.JwtRequestModel;
import com.org.securityservice.api.model.JwtResponseModel;
import com.org.securityservice.domain.service.token.TokenService;
import com.org.securityservice.domain.service.user.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public LoginController(TokenService tokenService, AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity createToken(@RequestBody JwtRequestModel request) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenService.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));

    }


}
