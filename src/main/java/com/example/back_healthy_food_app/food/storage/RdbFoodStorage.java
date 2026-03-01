package com.example.back_healthy_food_app.food.storage;


import com.example.back_healthy_food_app.errors.FoodNotFoundException;
import com.example.back_healthy_food_app.food.model.Food;
import com.example.back_healthy_food_app.food.model.FoodStorage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RdbFoodStorage implements FoodStorage {

    private final FoodRepository repository;

    public RdbFoodStorage(FoodRepository foodRepository) {this.repository = foodRepository;}

    @Override
    public List<Food> searchAllByName(String name,Integer page, Integer limit){
        Pageable pageable =  PageRequest.of(page,limit);

        return repository.findByNameContainingIgnoreCase(name, pageable)
                .stream()
                .map(FoodDBEntity::asFood)
                .toList();
    }

    @Override
    public Optional<Food> getByName(String name){
        return repository.findByName(name).map(FoodDBEntity::asFood);
    }

    @Override
    public void insert(Food food){
        FoodDBEntity foodDBEntity = new FoodDBEntity(food);
        repository.save(foodDBEntity);
    }

    @Override
    public Optional<Food> getById(String id) {
        return repository.findById(id).map(FoodDBEntity::asFood);
    }

    @Override
    public void update(String id, Food food) {
        Optional<FoodDBEntity> dbFood = repository.findById(id);
        if (dbFood.isEmpty()) {
            throw new FoodNotFoundException(id);
        }
        FoodDBEntity foodDBEntity = dbFood.get();
        foodDBEntity.setName(food.getName());
        foodDBEntity.setFatPer100(food.getFatPer100());
        foodDBEntity.setCarbsPer100(food.getCarbsPer100());
        foodDBEntity.setProteinPer100(food.getProteinPer100());
        foodDBEntity.setCaloriesPer100(food.getCaloriesPer100());
        repository.save(foodDBEntity);
    }

    @Override
    public void delete(String id) {
        Optional<FoodDBEntity> foodDB = repository.findById(id);
        if (foodDB.isEmpty()) {
            throw new FoodNotFoundException(id);
        }
        repository.delete(foodDB.get());
    }

    @Override
    public void createMany(List<Food> foods) {
        for (Food food : foods) {
            repository.save(new FoodDBEntity(food));
        }
    }





}
