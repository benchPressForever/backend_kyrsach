package com.example.back_healthy_food_app.food.controller;


import com.example.back_healthy_food_app.food.model.Food;
import com.example.back_healthy_food_app.food.model.FoodScenario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/food")
public class FoodController {

    private final FoodScenario scenario;

    public FoodController(FoodScenario scenario){
        this.scenario = scenario;
    }

    @GetMapping
    public List<Food> getAllByName(String name,Integer page, Integer limit){
        return scenario.GetByName(name,page,limit);
    }

    @GetMapping("{id}")
    public Food getById(@PathVariable String id){
        return scenario.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Food food){
        scenario.store(food);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id,@RequestBody Food food){
        scenario.update(id,food);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        scenario.delete(id);
    }

    @PostMapping("{list}")
    @ResponseStatus(HttpStatus.OK)
    public void createMany(@RequestBody  List<Food> foods){
        scenario.createMany(foods);
    }



}
