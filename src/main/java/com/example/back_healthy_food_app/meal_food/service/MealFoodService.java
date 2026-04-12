package com.example.back_healthy_food_app.meal_food.service;

import com.example.back_healthy_food_app.errors.MealFoodNotFoundException;
import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.food.service.FoodService;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodRequest;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal_food.dto.UpdateDtoMealFood;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodRepository;
import org.springframework.stereotype.Service;

@Service
public class MealFoodService implements IMealFoodService {

    private final MealFoodRepository mealFoodRepository;
    private final FoodService foodService;

    public MealFoodService(MealFoodRepository mealFoodRepository, FoodService foodService)
    {
        this.mealFoodRepository = mealFoodRepository;
        this.foodService = foodService;
    }

    @Override
    public MealFoodResponse get(String id){
        return mealFoodRepository.findById(id).
                map(MealFoodEntity::asMealFood).
                orElseThrow(() -> new MealFoodNotFoundException(id));
    }

    @Override
    public MealFoodResponse save(MealFoodRequest request) {
        FoodDBEntity foodEntity = foodService.getEntityById(request.getFoodId());

        MealFoodEntity mealFoodEntity = new MealFoodEntity(request, foodEntity);

        MealFoodEntity saved = mealFoodRepository.save(mealFoodEntity);

        return saved.asMealFood();
    }

    @Override
    public void delete(String id) {
        if (!mealFoodRepository.existsById(id)) {
            throw new MealFoodNotFoundException(id);
        }
        mealFoodRepository.deleteById(id);
    }

    @Override
    public MealFoodResponse update(String id, UpdateDtoMealFood dto) {
        MealFoodEntity mealFood =  mealFoodRepository.findById(id)
                .orElseThrow(() -> new MealFoodNotFoundException(id));

        mealFood.setServingSize(dto.getServingSize());

        return mealFoodRepository.save(mealFood).asMealFood();
    }


}
