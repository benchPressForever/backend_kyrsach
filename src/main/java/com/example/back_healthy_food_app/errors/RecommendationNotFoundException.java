package com.example.back_healthy_food_app.errors;

public class RecommendationNotFoundException extends RuntimeException {
    public RecommendationNotFoundException(String id) {
        super("Recommendation with id " + id + " not found");
    }
}
