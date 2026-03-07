package com.example.back_healthy_food_app.errors;

public class MealFoodNotFoundException extends RuntimeException {
    public MealFoodNotFoundException(String id) {
        super("MealFood with id " + id + " not found");
    }
}
