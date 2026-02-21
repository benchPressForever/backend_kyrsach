package com.example.back_healthy_food_app.model;


// Food - класс , описывающий сущность продукта
public class Food {

    private String name;
    private Double calories_per_100;
    private Double protein_per_100;
    private Double fat_per_100;
    private Double carbs_per_100;

    public Food(){}

    public Food(String name,Double calories_per_100,Double protein_per_100,Double fat_per_100,Double carbs_per_100){
        this.name = name;
        this.calories_per_100 = calories_per_100;
        this.protein_per_100 = protein_per_100;
        this.fat_per_100 = fat_per_100;
        this.carbs_per_100 = carbs_per_100;
    }


    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Double getCalories_per_100() {return calories_per_100;}

    public void setCalories_per_100(Double calories_per_100) {this.calories_per_100 = calories_per_100;}

    public Double getProtein_per_100() {return protein_per_100;}

    public void setProtein_per_100(Double protein_per_100) {this.protein_per_100 = protein_per_100;}

    public Double getFat_per_100() {return fat_per_100;}

    public void setFat_per_100(Double fat_per_100) {this.fat_per_100 = fat_per_100;}

    public Double getCarbs_per_100() {return carbs_per_100;}

    public void setCarbs_per_100(Double carbs_per_100) {this.carbs_per_100 = carbs_per_100;}

    public boolean isValid(){
        return  name != null && !name.isEmpty() &&
                protein_per_100 != null && protein_per_100 >= 0 &&
                fat_per_100 != null && fat_per_100 >= 0 &&
                carbs_per_100 != null && carbs_per_100 >= 0 &&
                calories_per_100 != null && calories_per_100 >= 0;
    }
}
