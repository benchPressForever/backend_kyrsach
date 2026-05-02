package com.example.back_healthy_food_app.meal_food.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class UpdateDtoMealFood {
    @Positive(message = "Размер порции должен быть положительным")
    private Float servingSize;

    public UpdateDtoMealFood() {}

    public UpdateDtoMealFood(Float servingSize) {
        this.servingSize = servingSize;
    }
}