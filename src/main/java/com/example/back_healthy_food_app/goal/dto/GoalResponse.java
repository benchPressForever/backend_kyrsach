package com.example.back_healthy_food_app.goal.dto;

import com.example.back_healthy_food_app.goal.storage.GoalEntity;
import lombok.Data;

@Data
public class GoalResponse {
    private String id;

    private Float calories;
    private Float protein;
    private Float fat;
    private Float carbs;

    private Integer mealsCount;

    private TypeGoal typeGoal;
    private TypeActivity typeActivity;
    public GoalResponse() {}
    public GoalResponse(GoalEntity goalEntity) {
        this.id = goalEntity.getId();
        this.calories = goalEntity.getCalories();
        this.fat = goalEntity.getFat();
        this.carbs = goalEntity.getCarbs();
        this.protein = goalEntity.getProtein();
        this.mealsCount = goalEntity.getMealsCount();
        this.typeGoal = goalEntity.getTypeGoal();
        this.typeActivity = goalEntity.getTypeActivity();
    }
}
