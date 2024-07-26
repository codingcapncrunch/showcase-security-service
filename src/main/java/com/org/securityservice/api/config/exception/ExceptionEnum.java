package com.org.securityservice.api.config.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    Unknown5000("Unknown exception", HttpStatus.INTERNAL_SERVER_ERROR),
    AUTH1000("Authentication exception", HttpStatus.BAD_REQUEST),
    AUTH1001("Authentication exception", HttpStatus.BAD_REQUEST),
    AUTH1002("Access denied", HttpStatus.UNAUTHORIZED),
    AUTH1003("Authentication exception", HttpStatus.UNAUTHORIZED),
    JWT1000("JWT Invalid", HttpStatus.BAD_REQUEST),
    JWT1001("JWT Malformed", HttpStatus.BAD_REQUEST),
    JWT1002("JWT Parse Exception", HttpStatus.BAD_REQUEST),
    JWT1003("JWT Expired", HttpStatus.BAD_REQUEST),
    JWT1004("JWT Missing", HttpStatus.BAD_REQUEST),
    PASS1000("password to encode cannot be null", HttpStatus.BAD_REQUEST),
    USER1000("Invalid user id", HttpStatus.BAD_REQUEST),
    USER1001("User not found", HttpStatus.BAD_REQUEST),
    USER1002("User disabled", HttpStatus.UNAUTHORIZED),
    USER1003("Invalid credentials", HttpStatus.UNAUTHORIZED),
    USER1004("Invalid user details for add user", HttpStatus.BAD_REQUEST),
    USER1005("Invalid user security for add user security", HttpStatus.BAD_REQUEST),
    USER1006("Invalid username provided", HttpStatus.BAD_REQUEST),
    USER1007("User security not found", HttpStatus.INTERNAL_SERVER_ERROR),

    MNGUSER1000("Invalid manager user request", HttpStatus.BAD_REQUEST),
    MNGUSER1001("Invalid username and/or role for add role request", HttpStatus.BAD_REQUEST),

    USERROLE1000("Invalid user role provided", HttpStatus.BAD_REQUEST);






    public final String message;
    public final HttpStatus httpStatus;

    ExceptionEnum(String message, HttpStatus status) {
        this.message = message;
        this.httpStatus = status;
    }

    public String getMessage(){
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
