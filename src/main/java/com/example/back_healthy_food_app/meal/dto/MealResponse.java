package com.example.back_healthy_food_app.meal.dto;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MealResponse {

    private String id;

    private String name;
    private String notes;
    private Date time;

    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;

    private List<MealFoodResponse> mealFoods;

    public MealResponse() {}

    public MealResponse(MealEntity mealEntity) {
        this.id = mealEntity.getId();
        this.name = mealEntity.getName();
        this.notes = mealEntity.getNotes();
        this.calories = mealEntity.getCalories();
        this.protein = mealEntity.getProtein();
        this.carbs = mealEntity.getCarbs();
        this.fats = mealEntity.getFat();
        this.time = mealEntity.getTime();
        this.mealFoods = new ArrayList<>();
    }
}
