package com.example.back_healthy_food_app.meal_food.storage;


import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodRequest;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id",nullable = false)
    private FoodDBEntity food;


    public MealFoodEntity() {}

    public MealFoodEntity(MealFoodRequest mealFood,FoodDBEntity food) {
        this.servingSize = mealFood.getServingSize();
        this.food = food;
    }

    public MealFoodResponse asMealFood() {
        return new MealFoodResponse(this);
    }
}
