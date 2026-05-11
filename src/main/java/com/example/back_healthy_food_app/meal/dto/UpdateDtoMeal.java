package com.example.back_healthy_food_app.meal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateDtoMeal {

    @Size(min = 2, max = 50, message = "Название должно быть от 2 до 50 символов")
    private String name;

    @Size(max = 200, message = "Заметки не могут быть длиннее 200 символов")
    private String notes;

    public UpdateDtoMeal() {}

    public UpdateDtoMeal(String name, String notes) {
        this.name = name;
        this.notes = notes;
    }
}
