package com.example.back_healthy_food_app.food.service;


import com.example.back_healthy_food_app.errors.FoodNotFoundException;
import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.food.dto.FoodGetDto;
import com.example.back_healthy_food_app.food.dto.UpdateDtoFood;
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
    public List<Food> searchAllByName(FoodGetDto dto){
        Pageable pageable =  PageRequest.of(dto.getPage()-1, dto.getLimit());

        return repository.findByNameContainingIgnoreCaseOrderByNameDesc(dto.getName(),pageable)
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
    public Food update(String id, UpdateDtoFood dto) {
        FoodDBEntity foodDBEntity = repository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException(id));

        if(dto.getName() != null) foodDBEntity.setName(dto.getName());
        if(dto.getFatPer100() != null) foodDBEntity.setFatPer100(dto.getFatPer100());
        if(dto.getCarbsPer100() != null) foodDBEntity.setCarbsPer100(dto.getCarbsPer100());
        if(dto.getProteinPer100() != null) foodDBEntity.setProteinPer100(dto.getProteinPer100());
        if(dto.getCaloriesPer100() != null) foodDBEntity.setCaloriesPer100(dto.getCaloriesPer100());

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
