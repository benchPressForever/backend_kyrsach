package com.example.back_healthy_food_app.meal_food.dto;

import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import lombok.Data;

@Data
public class MealFoodResponse {

    private String id;

    private Float servingSize;

    private Float calories;
    private Float protein;
    private Float carbs;
    private Float fat;

    private String mealId;
    private Food  food;

    public MealFoodResponse() {}

    public MealFoodResponse(MealFoodEntity  mealFoodEntity) {
        this.id = mealFoodEntity.getId();
        this.servingSize = mealFoodEntity.getServingSize();
        this.mealId = mealFoodEntity.getMeal().getId();

        if (this.servingSize == null) {
            this.servingSize = 0.0f;
        }

        float multiplier = servingSize / 100;

        FoodDBEntity food = mealFoodEntity.getFood();
        if(food != null) {
            this.food = food.asFood();
            this.calories = getNutrientValue(food.getCaloriesPer100(), multiplier);
            this.protein = getNutrientValue(food.getProteinPer100(), multiplier);
            this.carbs = getNutrientValue(food.getCarbsPer100(), multiplier);
            this.fat = getNutrientValue(food.getFatPer100(), multiplier);
        }
    }

    private float getNutrientValue(Float value, float multiplier) {
        float baseValue = value != null ? value : 0.0f;
        float result = baseValue * multiplier;
        return Math.round(result * 100.0) / 100.0f;
    }

}
