package com.example.Tech_Trends.exceptions;

import java.io.IOException;

public class TechTrendFailedUpload extends RuntimeException {
    public TechTrendFailedUpload(String message) {
        super(message);
    }
}
