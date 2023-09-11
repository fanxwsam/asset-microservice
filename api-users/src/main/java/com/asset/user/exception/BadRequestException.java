package com.asset.user.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String messageIn){
        super(messageIn);
    }

    public BadRequestException(String messageIn, Throwable causeIn){
        super(messageIn, causeIn);
    }
}
