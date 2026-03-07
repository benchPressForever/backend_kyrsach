package com.example.back_healthy_food_app.meal_food.controller;

import com.example.back_healthy_food_app.food.model.Food;
import com.example.back_healthy_food_app.food.model.FoodScenario;
import com.example.back_healthy_food_app.meal_food.model.MealFood;
import com.example.back_healthy_food_app.meal_food.model.MealFoodScenario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/meal_food")
public class MealFoodController {

    private final MealFoodScenario scenario;

    public MealFoodController(MealFoodScenario scenario) {
        this.scenario = scenario;
    }

    @GetMapping("{id}")
    public MealFood get(@PathVariable String id){
        return scenario.getById(id);
    }
}
