package com.example.back_healthy_food_app.meal_food.controller;

import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodRequest;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal_food.dto.UpdateDtoMealFood;
import com.example.back_healthy_food_app.meal_food.service.MealFoodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/meal_food")
public class MealFoodController {

    private final MealFoodService service;

    public MealFoodController(MealFoodService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public MealFoodResponse get(@PathVariable String id){
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealFoodResponse create(@Valid @RequestBody MealFoodRequest mealFood){
        return service.save(mealFood);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealFoodResponse update(@PathVariable String id,@Valid @RequestBody UpdateDtoMealFood dto){
        return service.update(id,dto);
    }
}
