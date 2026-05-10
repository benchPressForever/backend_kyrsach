package com.example.back_healthy_food_app.meal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MealRequest {

    @NotBlank(message = "Название приёма пищи обязателен")
    private String name;

    @NotBlank(message = "Записка приёма пищи обязателена")
    private String notes;

    @NotBlank(message = "Поле DailyId обязательно!")
    private String dailyId;

    public MealRequest() {}

    public MealRequest(String name, String notes,String dailyId) {
        this.name = name;
        this.notes = notes;
        this.dailyId = dailyId;
    }
}
