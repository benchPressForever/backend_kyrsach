package com.example.back_healthy_food_app.meal.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateDtoMeal {
    @NotBlank(message = "Meal name must be a string")
    private String name;

    @NotBlank(message = "Notes must be a string")
    private String notes;

    public UpdateDtoMeal(String name,String notes){
        this.name = name;
        this.notes = notes;
    };
}
