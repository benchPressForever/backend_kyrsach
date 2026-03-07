package com.example.back_healthy_food_app.meal_food.storage;

import com.example.back_healthy_food_app.errors.MealFoodNotFoundException;
import com.example.back_healthy_food_app.meal_food.model.MealFood;
import com.example.back_healthy_food_app.meal_food.model.MealFoodStorage;
import org.springframework.stereotype.Repository;

@Repository
public class RdbMealFoodStorage implements MealFoodStorage {

    private final MealFoodRepository repository;

    public RdbMealFoodStorage(MealFoodRepository repository) {this.repository = repository;}

    @Override
    public MealFood getMealFood(String id){
        return repository.findById(id).map(MealFoodEntity::asMealFood).orElseThrow(() -> new MealFoodNotFoundException(id));
    }


}
