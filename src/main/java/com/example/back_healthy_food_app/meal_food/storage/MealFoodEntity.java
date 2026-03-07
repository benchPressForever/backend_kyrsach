package com.example.back_healthy_food_app.meal_food.storage;


import com.example.back_healthy_food_app.food.model.Food;
import com.example.back_healthy_food_app.meal_food.model.MealFood;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "meal_food")
@Data
public class MealFoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="serving_size",nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private Double servingSize;

    @Column(name="calculated_calories",nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private Double calculatedCalories;

    @Column(name="calculated_protein",nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private Double calculatedProtein;

    @Column(name="calculated_fat",nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private Double calculatedFat;

    @Column(name="calculated_carbs",nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private Double calculatedCarbs;

    @Column(name="meal_id", nullable = false)
    private String mealId;

    @Column(name="food_id", nullable = false)
    private String foodId;


    public MealFoodEntity() {}

    public MealFoodEntity(String id,Double servingSize,Double calculatedCalories,Double calculatedProtein,Double calculatedCarbs,Double calculatedFat,String mealId, String foodId) {
        this.id = id;
        this.servingSize = servingSize;
        this.calculatedCalories = calculatedCalories;
        this.calculatedProtein = calculatedProtein;
        this.calculatedCarbs = calculatedCarbs;
        this.calculatedFat = calculatedFat;
        this.mealId = mealId;
        this.foodId = foodId;
    }


    public MealFood asMealFood() {
        return new MealFood(id,servingSize,calculatedCalories,calculatedCarbs,calculatedProtein,calculatedFat,mealId,foodId);
    }




}
