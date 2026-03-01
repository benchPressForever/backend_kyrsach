package com.example.back_healthy_food_app.configuration;


import com.example.back_healthy_food_app.food.model.FoodScenario;
import com.example.back_healthy_food_app.food.storage.FoodRepository;
import com.example.back_healthy_food_app.food.storage.RdbFoodStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration {

    private final FoodRepository foodRepository;

    public ComponentConfiguration(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    @Bean
    public FoodScenario foodScenario(){return new FoodScenario(new RdbFoodStorage(foodRepository));}

}
