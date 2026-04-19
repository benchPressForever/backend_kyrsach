package com.example.back_healthy_food_app.meal.storage;

import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodRequest;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "meal")
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name",nullable = false,unique = true)
    private String name;

    @Column(name="calories", columnDefinition = "FLOAT DEFAULT 0.0")
    private Double calories;

    @Column(name="protein",columnDefinition = "FLOAT DEFAULT 0.0")
    private Double protein;

    @Column(name="fat",columnDefinition = "FLOAT DEFAULT 0.0")
    private Double fat;

    @Column(name="carbs",columnDefinition = "FLOAT DEFAULT 0.0")
    private Double carbs;

    @Column(name="notes",nullable = false)
    private String notes;

    @Column(name = "time",
            columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'Europe/Moscow')")
    private Date time;

    @OneToMany(mappedBy = "meal",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<MealFoodEntity> mealFoods = new ArrayList<>();

    public MealEntity() {}

    public MealEntity(MealRequest request) {
        this.name = request.getName();
        this.notes = request.getNotes();
        this.calories = 0.0;
        this.protein = 0.0;
        this.carbs = 0.0;
        this.fat = 0.0;
        this.time = new Date();
    }

    public MealResponse asMeal() {
        return new MealResponse(this);
    }
}
