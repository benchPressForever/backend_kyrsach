package com.example.back_healthy_food_app.errors;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super("User with email " + email + "is not found");
    }
}
