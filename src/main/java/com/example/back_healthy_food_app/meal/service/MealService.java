package com.example.back_healthy_food_app.meal.service;

import com.example.back_healthy_food_app.errors.MealFoodNotFoundException;
import com.example.back_healthy_food_app.errors.MealNotFoundException;
import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal.dto.UpdateDtoMeal;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import com.example.back_healthy_food_app.meal.storage.MealRepository;
import com.example.back_healthy_food_app.user.service.UserService;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class MealService implements IMealService {
    private final MealRepository repository;
    private final UserService userService;

    public MealService(MealRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public MealResponse insert(MealRequest meal,String userId) {
        UserEntity user = userService.getEntityById(userId);
        MealEntity mealEntity = new MealEntity(meal,user);
        return repository.save(mealEntity).asMeal();
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new MealFoodNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public MealResponse update(String id, UpdateDtoMeal dto) {
        MealEntity meal =  repository.findById(id)
                .orElseThrow(() -> new MealNotFoundException(id));

        meal.setName(dto.getName());
        meal.setNotes(dto.getNotes());

        return repository.save(meal).asMeal();
    }

    @Override
    public MealResponse get(String id) {
        return repository.findById(id).
                map(MealEntity::asMeal).
                orElseThrow(() -> new MealNotFoundException(id));
    }

    public MealEntity getEntityById(String id) {
        return repository.findById(id).
                orElseThrow(() -> new MealNotFoundException(id));
    }
}
