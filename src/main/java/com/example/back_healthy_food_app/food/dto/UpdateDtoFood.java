package com.example.back_healthy_food_app.food.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateDtoFood {

    @Size(min = 2, max = 100, message = "Название продукта должно быть от 2 до 100 символов")
    private String name;

    @PositiveOrZero(message = "Калорийность не может быть отрицательной")
    private Float caloriesPer100;

    @PositiveOrZero(message = "Количество белков не может быть отрицательным")
    private Float proteinPer100;

    @PositiveOrZero(message = "Количество жиров не может быть отрицательным")
    private Float fatPer100;

    @PositiveOrZero(message = "Количество углеводов не может быть отрицательным")
    private Float carbsPer100;
}
