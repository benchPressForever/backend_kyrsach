package com.example.back_healthy_food_app.food.storage;


import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
@Data
public class FoodDBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name",nullable = false,unique = true)
    private String name;

    @Column(name="calories_per_100",nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private Double caloriesPer100;

    @Column(name="protein_per_100",nullable = false,columnDefinition = "FLOAT DEFAULT 0.0")
    private Double proteinPer100;

    @Column(name="fat_per_100",nullable = false ,columnDefinition = "FLOAT DEFAULT 0.0")
    private Double fatPer100;

    @Column(name="carbs_per_100",nullable = false,columnDefinition = "FLOAT DEFAULT 0.0")
    private Double carbsPer100;

    @OneToMany(mappedBy = "food",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    private List<MealFoodEntity> mealFoods = new ArrayList<>();

    public void addMealFood(MealFoodEntity mealFood) {
        mealFoods.add(mealFood);
        mealFood.setFood(this);  // Синхронизация обратной стороны
    }

    public void removeMealFood(MealFoodEntity mealFood) {
        mealFoods.remove(mealFood);
        mealFood.setFood(null);
    }

    public FoodDBEntity() {}

    public FoodDBEntity(Food food){
        this.name = food.getName();
        this.caloriesPer100 = food.getCaloriesPer100();
        this.proteinPer100 = food.getProteinPer100();
        this.fatPer100 = food.getFatPer100();
        this.carbsPer100 = food.getCarbsPer100();
    }


    public Food asFood() {
        return new Food(id,name,caloriesPer100,proteinPer100,fatPer100,carbsPer100);
    }
}
