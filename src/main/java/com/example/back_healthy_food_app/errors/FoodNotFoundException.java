package com.example.back_healthy_food_app.errors;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(String id) {
        super("Food with id " + id + " not found");
    }
}
