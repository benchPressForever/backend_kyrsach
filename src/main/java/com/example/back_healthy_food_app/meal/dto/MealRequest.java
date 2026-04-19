package com.example.back_healthy_food_app.meal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MealRequest {

    @NotBlank(message = "Название приёма пищи обязателен")
    private String name;

    @NotBlank(message = "Записка приёма пищи обязателена")
    private String notes;

    public MealRequest() {}

    public MealRequest(String name, String notes) {
        this.name = name;
        this.notes = notes;
    }
}
