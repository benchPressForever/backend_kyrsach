package com.example.back_healthy_food_app.goal.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class UpdateDtoGoal {

    @DecimalMin(value = "0", message = "Количество приемов пищи должно быть >= 0")
    private Integer mealsCount;

    private TypeGoal typeGoal;//пере
    private TypeActivity typeActivity;//пере

    public UpdateDtoGoal(){}

    public UpdateDtoGoal(Integer mealsCount,TypeGoal typeGoal,TypeActivity typeActivity){
        this.mealsCount = mealsCount;
        this.typeGoal = typeGoal;
        this.typeActivity = typeActivity;
    }
}
