package com.example.back_healthy_food_app.Recommendation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateDtoRecommandation {

    @Size(max = 100, message = "Заголовок не должен превышать 100 символов")
    private String title;

    @Size(max = 300, message = "Текст не должен превышать 300 символов")
    private String text;
}
