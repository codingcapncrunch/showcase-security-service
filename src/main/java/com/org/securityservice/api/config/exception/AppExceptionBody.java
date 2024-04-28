package com.org.securityservice.api.config.exception;

import lombok.Data;

@Data
public class AppExceptionBody {

    private String userMessage;
    private String status;
    private String error;
    private String errorCode;

    public AppExceptionBody(String userMessage, String status, String error, String errorCode) {
        this.userMessage = userMessage;
        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
    }
}
