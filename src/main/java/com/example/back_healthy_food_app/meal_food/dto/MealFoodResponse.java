package com.example.back_healthy_food_app.meal_food.dto;

import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import lombok.Data;

@Data
public class MealFoodResponse {

    private String id;
    private Double servingSize;
    private String foodId;

    private String foodName;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fats;

    public MealFoodResponse() {}

    public MealFoodResponse(MealFoodEntity  mealFoodEntity) {
        this.id = mealFoodEntity.getId();
        this.servingSize = mealFoodEntity.getServingSize();

        if (this.servingSize == null) {
            this.servingSize = 0.0;
        }

        double multiplier = servingSize / 100;

        FoodDBEntity food = mealFoodEntity.getFood();
        if(food != null) {
            this.foodId = food.getId();
            this.foodName = food.getName();
            this.calories = (food.getCaloriesPer100() != null ? food.getCaloriesPer100() : 0.0) * multiplier;
            this.protein = (food.getProteinPer100() != null ? food.getProteinPer100() : 0.0) * multiplier;
            this.carbs = (food.getCarbsPer100() != null ? food.getCarbsPer100() : 0.0) * multiplier;
            this.fats = (food.getFatPer100() != null ? food.getFatPer100() : 0.0) * multiplier;
        }
    }

}
