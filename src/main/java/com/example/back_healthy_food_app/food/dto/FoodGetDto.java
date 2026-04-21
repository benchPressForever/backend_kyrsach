package com.example.back_healthy_food_app.food.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FoodGetDto {

    @NotNull(message = "Название не может быть null")
    @Size(min = 1, message = "Название не может быть пустым")
    private String name;

    @Min(value = 1, message = "Page не может быть меньше 1")
    private Integer page = 1;

    @Min(value = 1, message = "Limit не может быть меньше 1")
    @Max(value = 100, message = "Limit не может быть больше 100")
    private Integer limit = 10;

    public FoodGetDto(){}

    public FoodGetDto(String name,Integer page,Integer limit){
        this.name = name;
        this.page = page;
        this.limit = limit;
    }
}
