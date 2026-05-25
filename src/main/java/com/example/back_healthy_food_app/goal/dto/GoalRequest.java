package com.example.back_healthy_food_app.goal.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class GoalRequest {

    @NotNull(message = "Height cannot be null")
    @Min(value = 50, message = "Height must be at least 50 cm")
    @Max(value = 300, message = "Height must be at most 300 cm")
    private Integer height;

    @NotNull(message = "Weight cannot be null")
    @Min(value = 30, message = "Weight must be at least 2 kg")
    @Max(value = 300, message = "Weight must be at most 500 kg")
    private Integer weight;

    @NotNull(message = "Age cannot be null")
    @Min(value = 6, message = "Age must be at least 0")
    @Max(value = 110, message = "Age must be at most 150")
    private Integer age;

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
                       Gender gender, Integer height, Integer weight, Integer age){
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
    public static Double getActivityMultiplier(TypeActivity activity) {
        return activityMultipliers.get(activity);
    }
    private static final Map<TypeGoal, Map<String, Double>> goalConfig = new HashMap<>() {{
        put(TypeGoal.weightLoss, new HashMap<>() {{
            put("proteinMultiplier", 1.6);
            put("fatRatio", 0.25);
            put("carbRatio", 0.35);
            put("calorieAdjustment", 0.85);
        }});
        put(TypeGoal.weightGain, new HashMap<>() {{
            put("proteinMultiplier", 1.8);
            put("fatRatio", 0.25);
            put("carbRatio", 0.45);
            put("calorieAdjustment", 1.15);
        }});
        put(TypeGoal.weightSupport, new HashMap<>() {{
            put("proteinMultiplier", 1.2);
            put("fatRatio", 0.25);
            put("carbRatio", 0.45);
            put("calorieAdjustment", 1.0);
        }});
    }};
    public static Map<String, Double> getGoalConfigAll(TypeGoal goal) {
        return goalConfig.get(goal);
    }
    public static Double getGoalConfigValue(TypeGoal goal, String key) {
        Map<String, Double> config = goalConfig.get(goal);
        return config != null ? config.get(key) : null;
    }
}
