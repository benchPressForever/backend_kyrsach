package com.example.back_healthy_food_app.meal_food.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MealFoodRequest {


    @Positive(message = "Размер порции должен быть положительным числом")
    private Double servingSize;

    @NotBlank(message = "ID приёма пищи обязателен")
    private String mealId;

    @NotBlank(message = "ID продукта обязателен")
    private String foodId;


    public MealFoodRequest() {}

    public MealFoodRequest(Double servingSize, String mealId, String foodId) {
        this.servingSize = servingSize;
        this.mealId = mealId;
        this.foodId = foodId;
    }


}
