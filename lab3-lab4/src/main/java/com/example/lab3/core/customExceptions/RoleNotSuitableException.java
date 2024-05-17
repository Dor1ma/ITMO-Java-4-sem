package com.example.lab3.core.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RoleNotSuitableException extends RuntimeException
{
    public RoleNotSuitableException(String message)
    {
        super(message);
    }
}