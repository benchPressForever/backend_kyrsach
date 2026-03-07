package com.example.back_healthy_food_app.meal_food.model;

import com.example.back_healthy_food_app.errors.FoodNotFoundException;
import com.example.back_healthy_food_app.food.model.Food;
import com.example.back_healthy_food_app.food.model.FoodStorage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealFoodScenario {

    private  final MealFoodStorage storage;

    public MealFoodScenario(MealFoodStorage storage){this.storage = storage;}

    public MealFood getById(String id){
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id is null or empty");
        }
        return storage.getMealFood(id);
    }
}
