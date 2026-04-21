package com.example.back_healthy_food_app.food.controller;


import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.food.dto.FoodGetDto;
import com.example.back_healthy_food_app.food.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/food")
public class FoodController {

    private final FoodService service;

    public FoodController(FoodService service){
        this.service = service;
    }

    @GetMapping
    public List<Food> getAllByName(@Valid @ModelAttribute FoodGetDto dto){
        return service.searchAllByName(dto);
    }

    @GetMapping("{id}")
    public Food getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Food create(@Valid @RequestBody Food food){
        return service.insert(food);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Food update(@PathVariable String id,@Valid @RequestBody Food food){
        return service.update(id,food);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @PostMapping("{list}")
    @ResponseStatus(HttpStatus.OK)
    public List<Food> createMany(@Valid @RequestBody  List<Food> foods){
        return service.createMany(foods);
    }



}
