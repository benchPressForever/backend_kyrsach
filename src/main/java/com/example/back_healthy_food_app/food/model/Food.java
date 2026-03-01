package com.example.back_healthy_food_app.food.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
// Food - класс, описывающий сущность продукта
public class Food {

    private String id;

    private String name;

    @JsonProperty("caloriesPer100")
    private Double caloriesPer100;

    @JsonProperty("proteinPer100")
    private Double proteinPer100;

    @JsonProperty("fatPer100")
    private Double fatPer100;

    @JsonProperty("carbsPer100")
    private Double carbsPer100;

    public Food() {}

    public Food(String id,String name, Double caloriesPer100, Double proteinPer100,
                Double fatPer100, Double carbsPer100) {
        this.name = name;
        this.caloriesPer100 = caloriesPer100;
        this.proteinPer100 = proteinPer100;
        this.fatPer100 = fatPer100;
        this.carbsPer100 = carbsPer100;
        this.id = id;
    }


    public boolean isValid() {
        return name != null && !name.trim().isEmpty() &&
                proteinPer100 != null && proteinPer100 >= 0 &&
                fatPer100 != null && fatPer100 >= 0 &&
                carbsPer100 != null && carbsPer100 >= 0 &&
                caloriesPer100 != null && caloriesPer100 >= 0;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", caloriesPer100=" + caloriesPer100 +
                ", proteinPer100=" + proteinPer100 +
                ", fatPer100=" + fatPer100 +
                ", carbsPer100=" + carbsPer100 +
                '}';
    }
}