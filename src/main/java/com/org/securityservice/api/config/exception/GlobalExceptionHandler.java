package com.org.securityservice.api.config.exception;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<Object> handleAppException(AppException ex){
        if (ex.getInternalMessage()!=null){
            log.error("ErrorCode: {}, AppException: {}, Status: {}, internal message: {}", ex.getExceptionEnum().name(), ex.getUserMessage(), ex.getStatus().toString(), ex.getInternalMessage());
        } else {
            log.error("ErrorCode: {}, AppException: {}, Status: {}", ex.getExceptionEnum().name(), ex.getUserMessage(), ex.getStatus().toString());
        }
        return new ResponseEntity<>(ex.getAppExceptionBody(), ex.getStatus());
    }

    @ExceptionHandler(value = {InternalAuthenticationServiceException.class})
    public ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex){
        AppException appException = new AppException(ExceptionEnum.AUTH1001);
        if (ex.getMessage()!=null) {
            log.error("ErrorCode: {}, AppException: {}, InternalMessage: {}", appException.getExceptionEnum().name(), appException.getUserMessage(), ex.getMessage());
        } else {
            log.error("ErrorCode: {}, AppException: {}", appException.getExceptionEnum().name(), appException.getUserMessage());
        }
        return new ResponseEntity<>(appException.getAppExceptionBody(), appException.getStatus());
    }

//    SignatureException
    @ExceptionHandler(value = {SignatureException.class})
    public ResponseEntity<Object> handleSignatureException(SignatureException ex){
        AppException appException = new AppException(ExceptionEnum.JWT1000);
        if (ex.getMessage()!=null) {
            log.error("ErrorCode: {}, AppException: {}, InternalMessage: {}", appException.getExceptionEnum().name(), appException.getUserMessage(), ex.getMessage());
        } else {
            log.error("ErrorCode: {}, AppException: {}", appException.getExceptionEnum().name(), appException.getUserMessage());
        }
        return new ResponseEntity<>(appException.getAppExceptionBody(), appException.getStatus());
    }

//    MalformedJwtException
    @ExceptionHandler(value = {MalformedJwtException.class})
    public ResponseEntity<Object> handleSignatureException(MalformedJwtException ex){
        AppException appException = new AppException(ExceptionEnum.JWT1001);
        if (ex.getMessage()!=null) {
            log.error("ErrorCode: {}, AppException: {}, InternalMessage: {}", appException.getExceptionEnum().name(), appException.getUserMessage(), ex.getMessage());
        } else {
            log.error("ErrorCode: {}, AppException: {}", appException.getExceptionEnum().name(), appException.getUserMessage());
        }
        return new ResponseEntity<>(appException.getAppExceptionBody(), appException.getStatus());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleException(AccessDeniedException ex){
        AppException appException = new AppException(ExceptionEnum.AUTH1002);
        log.error("ErrorCode: {}, Unknown exception message: {}, ex stacktrace: {}", appException.getExceptionEnum().name(), ex.getMessage(), ex.toString());
        return new ResponseEntity<>(appException.getAppExceptionBody(), appException.getStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex){
        AppException appException = new AppException(ExceptionEnum.Unknown5000);
        log.error("ErrorCode: {}, Unknown exception message: {}, ex stacktrace: {}", appException.getExceptionEnum().name(), ex.getMessage(), ex.toString());
        return new ResponseEntity<>(appException.getAppExceptionBody(), appException.getStatus());
    }


}
