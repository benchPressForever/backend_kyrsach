package com.example.back_healthy_food_app.storage;


import com.example.back_healthy_food_app.model.Food;
import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class FoodDBEntity {

    @Id
    private String id;

    @Column(name="name",nullable = false,unique = true)
    private String name;

    @Column(name="calories_per_100",nullable = false,columnDefinition = "FLOAT DEFAULT 0.0")
    private Double caloriesPer100;

    @Column(name="protein_per_100",nullable = false,columnDefinition = "FLOAT DEFAULT 0.0")
    private Double proteinPer100;

    @Column(name="fat_per_100",nullable = false,columnDefinition = "FLOAT DEFAULT 0.0")
    private Double fatPer100;

    @Column(name="carbs_per_100",nullable = false,columnDefinition = "FLOAT DEFAULT 0.0")
    private Double carbsPer100;

    public FoodDBEntity() {}

    public FoodDBEntity(Food food){
        this.name = food.getName();
        this.caloriesPer100 = food.getCalories_per_100();
        this.proteinPer100 = food.getProtein_per_100();
        this.fatPer100 = food.getFat_per_100();
        this.carbsPer100 = food.getCarbs_per_100();
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Double getCaloriesPer100() {return caloriesPer100;}
    public void setCaloriesPer100(Double caloriesPer100) {this.caloriesPer100 = caloriesPer100;}

    public Double getProteinPer100() {return proteinPer100;}
    public void setProteinPer100(Double proteinPer100) {this.proteinPer100 = proteinPer100;}


    public Double getFatPer100() {return fatPer100;}
    public void setFatPer100(Double fatPer100) {this.fatPer100 = fatPer100;}

    public Double getCarbsPer100() {return carbsPer100;}
    public void setCarbsPer100(Double carbsPer100) {this.carbsPer100 = carbsPer100;}

    public Food asFood() {
        return new Food(name,caloriesPer100,proteinPer100,fatPer100,carbsPer100);
    }
}
