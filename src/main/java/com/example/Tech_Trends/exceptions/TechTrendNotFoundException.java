package com.example.Tech_Trends.exceptions;

import lombok.experimental.SuperBuilder;

public class TechTrendNotFoundException extends RuntimeException {
    public TechTrendNotFoundException(String message) {
        super(message);
    }
}
