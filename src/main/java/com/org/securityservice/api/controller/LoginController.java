package com.org.securityservice.api.controller;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.api.model.JwtRequestModel;
import com.org.securityservice.api.model.JwtResponseModel;
import com.org.securityservice.domain.service.token.TokenService;
import com.org.securityservice.domain.service.user.JwtUserDetailsService;
import com.org.securityservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
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
            Utils.throwException(new AppException(ExceptionEnum.USER1002, request.getUsername()));
        } catch (BadCredentialsException e) {
            Utils.throwException(new AppException(ExceptionEnum.USER1003, request.getUsername()));
        } catch (InternalAuthenticationServiceException e){
            Utils.throwException(new AppException(ExceptionEnum.AUTH1003, e.getMessage()));
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenService.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));

    }


}
