package com.example.back_healthy_food_app.meal.storage;

import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodEntity;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meal")
@Data
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="calories",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float calories;

    @Column(name="protein",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float protein;

    @Column(name="fat",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float fat;

    @Column(name="carbs",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float carbs;

    @Column(name="notes",nullable = false)
    private String notes;

    @CreationTimestamp
    @Column(name = "time", nullable = false, updatable = false)
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "meal",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<MealFoodEntity> mealFoods = new ArrayList<>();

    public MealEntity() {}

    public MealEntity(MealRequest request,UserEntity user) {
        this.name = request.getName();
        this.notes = request.getNotes();
        this.calories = 0.0f;
        this.protein = 0.0f;
        this.carbs = 0.0f;
        this.fat = 0.0f;
        this.time = new Date();
        this.user = user;
    }

    public MealResponse asMeal() {
        return new MealResponse(this);
    }
}
