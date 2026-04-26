package com.example.back_healthy_food_app.errors;

public class DailyStatNotFoundException extends RuntimeException {
    public DailyStatNotFoundException(String id) {
        super("Daily Stat with id " + id + "is not found");
    }
}
