package com.example.back_healthy_food_app.daily_stat.storage;

import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "daily_stat",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_date",
                        columnNames = {"user_id", "date"})
        })
public class DailyStatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="weight",columnDefinition = "FLOAT DEFAULT 70")
    private Float weight;

    @Column(name="height",columnDefinition = "INTEGER DEFAULT 170")
    private Integer height;

    @Column(name="meals_count",columnDefinition = "INTEGER DEFAULT 3")
    private Integer mealsCount;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name="calories",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float calories;

    @Column(name="protein",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float protein;

    @Column(name="fat",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float fat;

    @Column(name="carbs",columnDefinition = "FLOAT DEFAULT 0.0")
    private Float carbs;

    @OneToMany(mappedBy = "daily",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<MealEntity> meals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    public DailyStatEntity() {}

    public DailyStatEntity(DailyStatRequest request,UserEntity user) {
        this.weight = request.getWeight();
        this.height = request.getHeight();
        this.mealsCount = request.getMealsCount();
        this.date = request.getDate();
        this.user = user;
        this.calories = 0.0f;
        this.protein = 0.0f;
        this.fat =  0.0f;
        this.carbs = 0.0f;
    }

    public DailyStatResponse asDailyStat(){
        return new DailyStatResponse(this);
    }
}
