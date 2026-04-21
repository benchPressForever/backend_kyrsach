package com.example.back_healthy_food_app.Recommendation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecommendationRequest {

    @NotBlank(message = "Заголовок рекомендации не может быть пустым")
    @Size(max = 100, message = "Заголовок не должен превышать 100 символов")
    private String title;

    @NotBlank(message = "Текст рекомендации не может быть пустым")
    private String text;

    public RecommendationRequest(String title, String text){
        this.title = title;
        this.text = text;
    };
}
