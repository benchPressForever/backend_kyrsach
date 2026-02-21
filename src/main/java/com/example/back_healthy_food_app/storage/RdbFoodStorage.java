package com.example.back_healthy_food_app.storage;


import com.example.back_healthy_food_app.model.Food;
import com.example.back_healthy_food_app.model.FoodStorage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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





}
