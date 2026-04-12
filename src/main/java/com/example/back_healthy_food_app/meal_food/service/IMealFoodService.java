package com.example.back_healthy_food_app.meal_food.service;

import com.example.back_healthy_food_app.meal_food.dto.MealFoodRequest;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal_food.dto.UpdateDtoMealFood;

public interface IMealFoodService {


    MealFoodResponse save(MealFoodRequest mealFood);

    void delete(String id);

    MealFoodResponse update(String id, UpdateDtoMealFood dto);

    MealFoodResponse get(String id);

}
