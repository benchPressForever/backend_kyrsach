package com.example.back_healthy_food_app.daily_stat.storage;

import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="daily_stat")
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
    }

    public DailyStatResponse asDailyStat(){
        return new DailyStatResponse(this);
    }
}
