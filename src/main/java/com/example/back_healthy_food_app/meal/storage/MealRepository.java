package com.example.back_healthy_food_app.meal.storage;

import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<MealEntity,String> {

}
