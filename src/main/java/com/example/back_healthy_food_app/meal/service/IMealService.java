package com.example.back_healthy_food_app.meal.service;

import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal.dto.UpdateDtoMeal;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodRequest;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal_food.dto.UpdateDtoMealFood;

import java.util.List;

public interface IMealService {

    MealResponse insert(MealRequest meal);

    void delete(String id);

    MealResponse update(String id, UpdateDtoMeal dto);

    MealResponse get(String id);

}
