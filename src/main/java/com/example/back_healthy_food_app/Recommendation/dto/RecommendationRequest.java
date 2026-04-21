package com.example.back_healthy_food_app.Recommendation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecommendationRequest {

    @NotBlank(message = "Recommendation title must be a string")
    @Size(max = 200, message = "Title is too long")
    private String title;

    @NotBlank(message = "Recommendation text must be a string")
    private String text;

    public RecommendationRequest(String title, String text){
        this.title = title;
        this.text = text;
    };
}
