package com.example.back_healthy_food_app.food.service;


import com.example.back_healthy_food_app.errors.FoodNotFoundException;
import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.food.storage.FoodRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService implements IFoodService {

    private final FoodRepository repository;

    public FoodService(FoodRepository foodRepository) {this.repository = foodRepository;}

    @Override
    public List<Food> searchAllByName(String name, Integer page, Integer limit){
        Pageable pageable =  PageRequest.of(page,limit);

        return repository.findByNameContainingIgnoreCase(name, pageable)
                .stream()
                .map(FoodDBEntity::asFood)
                .toList();
    }


    @Override
    public Food insert(Food food){
        FoodDBEntity foodDBEntity = new FoodDBEntity(food);
        return repository.save(foodDBEntity).asFood();
    }

    @Override
    public Food getById(String id) {
        return repository.findById(id).map(FoodDBEntity::asFood)
                .orElseThrow(() -> new FoodNotFoundException(id));
    }

    @Override
    public FoodDBEntity getEntityById(String id){
        return repository.findById(id).orElseThrow(() -> new FoodNotFoundException(id));
    }

    @Override
    public Food update(String id, Food food) {
        FoodDBEntity foodDBEntity = repository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        foodDBEntity.setName(food.getName());
        foodDBEntity.setFatPer100(food.getFatPer100());
        foodDBEntity.setCarbsPer100(food.getCarbsPer100());
        foodDBEntity.setProteinPer100(food.getProteinPer100());
        foodDBEntity.setCaloriesPer100(food.getCaloriesPer100());
        return repository.save(foodDBEntity).asFood();
    }

    @Override
    public void delete(String id) {
        FoodDBEntity foodDB = repository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        repository.delete(foodDB);
    }

    @Override
    public List<Food> createMany(List<Food> foods) {
        List<Food> foodList = new ArrayList<>();

        for (Food food : foods) {
            FoodDBEntity FoodEntity = repository.save(new FoodDBEntity(food));
            foodList.add(FoodEntity.asFood());
        }
        return foodList;
    }





}
