package com.example.back_healthy_food_app.meal.dto;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MealResponse {

    private String id;

    private String name;
    private String notes;
    private Date time;
    private String dailyId;

    private Float calories;
    private Float protein;
    private Float carbs;
    private Float fat;

    private List<MealFoodResponse> mealFoods;

    public MealResponse() {}

    public MealResponse(MealEntity mealEntity) {
        this.id = mealEntity.getId();
        this.name = mealEntity.getName();
        this.notes = mealEntity.getNotes();
        this.calories = mealEntity.getCalories();
        this.protein = mealEntity.getProtein();
        this.carbs = mealEntity.getCarbs();
        this.fat = mealEntity.getFat();
        this.time = mealEntity.getTime();
        this.mealFoods = mealEntity.getMealFoods().stream().map(MealFoodEntity::asMealFood).collect(Collectors.toList());
        this.dailyId =  mealEntity.getDaily().getId();
    }
}
