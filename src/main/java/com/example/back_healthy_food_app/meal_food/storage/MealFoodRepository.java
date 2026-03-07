package com.example.back_healthy_food_app.meal_food.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealFoodRepository extends JpaRepository<MealFoodEntity,String> {

}
