package com.example.back_healthy_food_app.meal_food.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Data
public class UpdateDtoMealFood {
    @PositiveOrZero(message = "Размер порции должен быть положительным")
    private Double servingSize;

    public UpdateDtoMealFood() {}

    public UpdateDtoMealFood(Double servingSize) {
        this.servingSize = servingSize;
    }
}