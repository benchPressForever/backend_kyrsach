package com.example.back_healthy_food_app.meal_food.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class MealFood {


    private String id;

    private Double servingSize;

    private Double calculatedCalories;

    private Double calculatedProtein;

    private Double calculatedFat;

    private Double calculatedCarbs;

    private String mealId;

    private String foodId;


    public MealFood() {}

    public MealFood(String id,Double servingSize,Double calculatedCalories,Double calculatedProtein,Double calculatedCarbs,Double calculatedFat,String mealId, String foodId) {
        this.id = id;
        this.servingSize = servingSize;
        this.calculatedCalories = calculatedCalories;
        this.calculatedProtein = calculatedProtein;
        this.calculatedCarbs = calculatedCarbs;
        this.calculatedFat = calculatedFat;
        this.mealId = mealId;
        this.foodId = foodId;
    }

    public boolean isValid() {
        return servingSize!= null && servingSize >= 0 &&
                calculatedCalories!= null && calculatedCalories >= 0 &&
                calculatedProtein!= null && calculatedProtein >= 0 &&
                calculatedCarbs!= null && calculatedCarbs >= 0 &&
                calculatedFat!= null && calculatedFat >= 0 &&
                mealId != null && !foodId.trim().isEmpty();

    }

}
