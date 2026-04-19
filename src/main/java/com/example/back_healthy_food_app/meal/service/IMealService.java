package com.example.back_healthy_food_app.meal.service;

import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal.dto.UpdateDtoMeal;


public interface IMealService {
    MealResponse insert(MealRequest mealFood);

    void delete(String id);

    MealResponse update(String id, UpdateDtoMeal dto);

    MealResponse get(String id);
}
