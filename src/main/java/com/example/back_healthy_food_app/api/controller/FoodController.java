package com.example.back_healthy_food_app.api.controller;


import com.example.back_healthy_food_app.model.Food;
import com.example.back_healthy_food_app.model.FoodScenario;
import com.example.back_healthy_food_app.model.FoodStorage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/food")
public class FoodController {

    private final FoodScenario foods;

    public FoodController(FoodScenario foods){
        this.foods = foods;
    }

    @GetMapping
    public List<Food> GetByName(String name,Integer page, Integer limit){
        return foods.GetByName(name,page,limit);
    }
}
