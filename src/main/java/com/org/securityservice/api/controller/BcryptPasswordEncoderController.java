package com.org.securityservice.api.controller;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController()
public class BcryptPasswordEncoderController {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public BcryptPasswordEncoderController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/encodePassword")
    public ResponseEntity bcryptPassword(@RequestParam(name = "pw") String password){
        if (StringUtils.isEmpty(password)){
            log.info("bCryptEncodedPassword: {}", this.passwordEncoder.encode(password));
            return ResponseEntity.ok("password encoded successfully - check logs");
        }
        Utils.throwException(new AppException(ExceptionEnum.PASS1000));
        return null;
    }
}
