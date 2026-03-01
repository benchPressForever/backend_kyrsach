package com.example.back_healthy_food_app.food.model;

import com.example.back_healthy_food_app.errors.FoodNotFoundException;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodScenario {

    private  final FoodStorage storage;

    public FoodScenario(FoodStorage storage){this.storage = storage;}

    public List<Food> GetByName(String name, Integer page, Integer limit){
        if(page == null || page < 1 || limit == null || limit < 1){
            throw new IllegalArgumentException("page or limit is null or empty");
        }
        return storage.searchAllByName(name,page,limit);
    }

    public void store(Food food){
        System.out.println("=== ПРОВЕРКА FOOD ===");
        System.out.println("food object: " + food.getCaloriesPer100());
        if(food == null || !food.isValid()){
            throw new IllegalArgumentException("food is null or empty");
        }
        Optional<Food> dbFood = storage.getByName(food.getName());
        if(dbFood.isPresent()){
            throw  new IllegalArgumentException("food already exists");
        }
        storage.insert(food);
    }

    public void delete(String id){
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id is null or empty");
        }
        storage.delete(id);
    }

    public Food getById(String id){
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id is null or empty");
        }
        return storage.getById(id).orElseThrow(() -> new FoodNotFoundException(id));
    }

    public void update(String id, Food food){
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id is null or empty");
        }
        if(food == null || !food.isValid()){
            throw new IllegalArgumentException("food is null or empty");
        }
        storage.update(id, food);
    }

    public void createMany(List<Food> foods){
        for(Food food : foods){
            if(food == null || !food.isValid()){
                throw new IllegalArgumentException("food is null or empty");
            }
        }
        storage.createMany(foods);
    }



}
