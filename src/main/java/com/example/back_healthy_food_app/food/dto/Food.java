package com.example.back_healthy_food_app.food.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Food {

    private String id;

    @NotBlank(message = "Название продукта обязательно")
    @Size(min = 2, max = 100, message = "Название продукта должно быть от 2 до 100 символов")
    private String name;

    @NotNull(message = "Калорийность на 100г обязательна")
    @PositiveOrZero(message = "Калорийность не может быть отрицательной")
    private Float caloriesPer100;

    @NotNull(message = "Белки на 100г обязательны")
    @PositiveOrZero(message = "Количество белков не может быть отрицательным")
    private Float proteinPer100;

    @NotNull(message = "Жиры на 100г обязательны")
    @PositiveOrZero(message = "Количество жиров не может быть отрицательным")
    private Float fatPer100;

    @NotNull(message = "Углеводы на 100г обязательны")
    @PositiveOrZero(message = "Количество углеводов не может быть отрицательным")
    private Float carbsPer100;

    public Food() {}

    public Food(String id,String name, Float caloriesPer100, Float proteinPer100,
                Float fatPer100, Float carbsPer100) {
        this.name = name;
        this.caloriesPer100 = caloriesPer100;
        this.proteinPer100 = proteinPer100;
        this.fatPer100 = fatPer100;
        this.carbsPer100 = carbsPer100;
        this.id = id;
    }
}