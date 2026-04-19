package com.example.back_healthy_food_app.meal.service;

import com.example.back_healthy_food_app.errors.FoodNotFoundException;
import com.example.back_healthy_food_app.errors.MealFoodNotFoundException;
import com.example.back_healthy_food_app.errors.MealNotFoundException;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal.dto.UpdateDtoMeal;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import com.example.back_healthy_food_app.meal.storage.MealRepository;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import org.springframework.stereotype.Service;

@Service
public class MealService implements IMealService {

    private final MealRepository mealRepository;
    private MealRepository repository;

    public MealService(MealRepository mealRepository) {
        this.repository = mealRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public MealResponse insert(MealRequest meal) {
        MealEntity mealEntity = new MealEntity(meal);
        return repository.save(mealEntity).asMeal();
    }

    @Override
    public MealResponse get(String id) {
        return mealRepository.findById(id).
                map(MealEntity::asMeal).
                orElseThrow(() -> new MealNotFoundException(id));
    }


    public MealEntity getEntityById(String id){
        return repository.findById(id).orElseThrow(() -> new FoodNotFoundException(id));
    }

    @Override
    public void delete(String id) {
        if(!mealRepository.existsById(id)){
            throw new MealNotFoundException(id);
        }
        mealRepository.deleteById(id);
    }

    @Override
    public MealResponse update(String id, UpdateDtoMeal dto) {
        MealEntity meal =  repository.findById(id)
                .orElseThrow(() -> new MealNotFoundException(id));

        meal.setName(dto.getName());
        meal.setNotes(dto.getNotes());

        return repository.save(meal).asMeal();
    }
}
