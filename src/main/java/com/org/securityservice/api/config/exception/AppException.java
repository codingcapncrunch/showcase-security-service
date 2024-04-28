package com.org.securityservice.api.config.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppException extends RuntimeException {

    private String internalMessage;
    private String userMessage;
    private HttpStatus status;
    private String error;
    private ExceptionEnum exceptionEnum;

    public AppException(ExceptionEnum exceptionEnum) {
        this.userMessage = exceptionEnum.getMessage();
        this.status = exceptionEnum.getHttpStatus();
        this.error = status.getReasonPhrase();
        this.exceptionEnum = exceptionEnum;
    }

    public AppException(ExceptionEnum exceptionEnum, String internalMessage) {
        this.internalMessage = internalMessage;
        this.userMessage = exceptionEnum.getMessage();
        this.status = exceptionEnum.getHttpStatus();
        this.error = status.getReasonPhrase();
        this.exceptionEnum = exceptionEnum;
    }

    public AppExceptionBody getAppExceptionBody(){
        return new AppExceptionBody(this.userMessage, this.getStatus().toString(), this.getStatus().getReasonPhrase(), this.exceptionEnum.name());
    }
}
