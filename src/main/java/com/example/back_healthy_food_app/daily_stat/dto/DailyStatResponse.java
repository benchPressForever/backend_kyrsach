package com.example.back_healthy_food_app.daily_stat.dto;

import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyStatResponse {

    private String id;
    private Float weight;
    private Integer height;
    private LocalDate date;
    private Integer mealsCount;

    private Float calories;
    private Float protein;
    private Float fat;
    private Float carbs;

    public DailyStatResponse() {}

    public DailyStatResponse(DailyStatEntity dailyStatEntity) {
        this.id = dailyStatEntity.getId();
        this.weight = dailyStatEntity.getWeight();
        this.height = dailyStatEntity.getHeight();
        this.date = dailyStatEntity.getDate();
        this.mealsCount = dailyStatEntity.getMealsCount();
        this.calories = 0.0F;
        this.protein = 0.0F;
        this.fat =  0.0F;
        this.carbs = 0.0F;
    }
}
