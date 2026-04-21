package com.example.back_healthy_food_app.food.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Food {

    private String id;

    @NotBlank(message = "Название продукта обязательно")
    @Size(min = 2, max = 100, message = "Название продукта должно быть от 2 до 100 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9\\s\\-]+$", message = "Название может содержать только буквы, цифры, пробелы и дефисы")
    private String name;

    @NotNull(message = "Калорийность на 100г обязательна")
    @PositiveOrZero(message = "Калорийность не может быть отрицательной")
    private Double caloriesPer100;

    @NotNull(message = "Белки на 100г обязательны")
    @PositiveOrZero(message = "Количество белков не может быть отрицательным")
    private Double proteinPer100;

    @NotNull(message = "Жиры на 100г обязательны")
    @PositiveOrZero(message = "Количество жиров не может быть отрицательным")
    private Double fatPer100;

    @NotNull(message = "Углеводы на 100г обязательны")
    @PositiveOrZero(message = "Количество углеводов не может быть отрицательным")
    private Double carbsPer100;

    public Food() {}

    public Food(String id,String name, Double caloriesPer100, Double proteinPer100,
                Double fatPer100, Double carbsPer100) {
        this.name = name;
        this.caloriesPer100 = caloriesPer100;
        this.proteinPer100 = proteinPer100;
        this.fatPer100 = fatPer100;
        this.carbsPer100 = carbsPer100;
        this.id = id;
    }
}