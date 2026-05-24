package com.example.back_healthy_food_app.errors;

public class GoalNotFoundException extends RuntimeException {
    public GoalNotFoundException(String id) {
        super("Goal with id " + id + " not found");
    }
}
