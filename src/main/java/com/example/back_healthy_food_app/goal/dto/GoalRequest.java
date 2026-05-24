package com.example.back_healthy_food_app.goal.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class GoalRequest {

    private String height;
    private String weight;
    private Date age;

    @DecimalMin(value = "0", message = "Количество приемов пищи должно быть >= 0")
    private Integer mealsCount;

    @NotNull(message = "Необходимо ввести тип цели")
    private TypeGoal typeGoal;

    @NotNull(message = "Необходимо ввести тип активности")
    private TypeActivity typeActivity;

    @NotNull(message = "Пол не может быть пустым")
    private Gender gender;

    public GoalRequest(){}

    public GoalRequest(Integer mealsCount,TypeGoal typeGoal,TypeActivity typeActivity,
                       Gender gender, String height, String weight, Date age){
        this.mealsCount = mealsCount;
        this.typeGoal = typeGoal;
        this.typeActivity = typeActivity;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    private static final Map<TypeActivity, Double> activityMultipliers = new HashMap<>() {
        {
            put(TypeActivity.sedentary, 1.2);
            put(TypeActivity.light, 1.375);
            put(TypeActivity.moderate, 1.55);
            put(TypeActivity.active, 1.725);
            put(TypeActivity.veryActive, 1.9);
        }
    };
}
