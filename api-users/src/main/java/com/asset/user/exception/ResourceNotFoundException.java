package com.asset.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messageIn){
        super(messageIn);
    }

    public ResourceNotFoundException(String messageIn, Throwable causeIn){
        super(messageIn, causeIn);
    }
}
