package com.example.back_healthy_food_app.configuration;


import com.example.back_healthy_food_app.food.model.FoodScenario;
import com.example.back_healthy_food_app.food.storage.FoodRepository;
import com.example.back_healthy_food_app.food.storage.RdbFoodStorage;
import com.example.back_healthy_food_app.meal_food.model.MealFoodScenario;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodRepository;
import com.example.back_healthy_food_app.meal_food.storage.RdbMealFoodStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration {

    private final FoodRepository foodRepository;
    private final MealFoodRepository mealFoodRepository;

    public ComponentConfiguration(FoodRepository foodRepository,MealFoodRepository mealFoodRepository) {

        this.foodRepository = foodRepository;
        this.mealFoodRepository = mealFoodRepository;
    }

    @Bean
    public FoodScenario foodScenario(){return new FoodScenario(new RdbFoodStorage(foodRepository));}

    @Bean
    public MealFoodScenario mealFoodScenario(){return new MealFoodScenario(new RdbMealFoodStorage(mealFoodRepository));}

}
