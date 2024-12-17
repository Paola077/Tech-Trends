package com.example.Tech_Trends.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TechTrendExistingUserException extends RuntimeException {
    public TechTrendExistingUserException(String message) {
        super(message);
    }
}
