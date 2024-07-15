package com.org.securityservice.api.controller;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController()
public class BcryptPasswordEncoderController {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public BcryptPasswordEncoderController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/encodePassword")
    public ResponseEntity bcryptPassword(@RequestParam(name = "pw") String password){
        if (!StringUtils.isEmpty(password)){
            String requestId = UUID.randomUUID().toString();
            log.info("RequestID: {}, bCryptEncodedPassword: {}", requestId, this.passwordEncoder.encode(password));
            return ResponseEntity.ok("password encoded successfully - check logs for request ID: "+ requestId);
        }
        ExceptionUtil.throwException(new AppException(ExceptionEnum.PASS1000));
        return null;
    }

}
