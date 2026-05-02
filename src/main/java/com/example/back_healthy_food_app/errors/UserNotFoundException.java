package com.example.back_healthy_food_app.errors;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User with id " + id + "is not found");
    }
}
