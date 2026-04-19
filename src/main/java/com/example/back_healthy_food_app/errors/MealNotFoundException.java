package com.example.back_healthy_food_app.errors;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String id) {
        super("Meal with id " + id + " not found");
    }
}
