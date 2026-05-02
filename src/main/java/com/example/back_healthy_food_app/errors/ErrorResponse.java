package com.example.back_healthy_food_app.errors;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private Map<String, String> errors;
    private String timestamp;

    public ErrorResponse() {
        this.errors = new HashMap<>();
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorResponse(int status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this(status, message);
        this.errors = errors != null ? errors : new HashMap<>();
    }
    public void addError(String field, String errorMessage) {
        this.errors.put(field, errorMessage);
    }
}