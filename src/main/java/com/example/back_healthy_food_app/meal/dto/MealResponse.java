package com.example.back_healthy_food_app.meal.dto;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MealResponse {
    private String id;
    private String name;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
    private String notes;
    private Date time;
    private List<MealFoodResponse> mealFoods;

    public MealResponse(MealEntity mealEntity) {
        this.id = mealEntity.getId();
        this.name = mealEntity.getName();
        this.notes = mealEntity.getNotes();
        this.calories = mealEntity.getCalories();
        this.protein = mealEntity.getProtein();
        this.fat = mealEntity.getFat();
        this.carbs = mealEntity.getCarbs();
        this.time = mealEntity.getTime();
        this.mealFoods = new ArrayList<>();
    }
}
