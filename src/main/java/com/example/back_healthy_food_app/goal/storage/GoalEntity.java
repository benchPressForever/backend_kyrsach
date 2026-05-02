package com.example.back_healthy_food_app.goal.storage;


import com.example.back_healthy_food_app.goal.dto.GoalRequest;
import com.example.back_healthy_food_app.goal.dto.GoalResponse;
import com.example.back_healthy_food_app.goal.dto.TypeActivity;
import com.example.back_healthy_food_app.goal.dto.TypeGoal;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "goal")
@Data
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="calories",columnDefinition = "Float DEFAULT 0.0")
    private Float calories;

    @Column(name="protein",columnDefinition = "Float DEFAULT 0.0")
    private Float protein;

    @Column(name="fat",columnDefinition = "Float DEFAULT 0.0")
    private Float fat;

    @Column(name="carbs",columnDefinition = "Float DEFAULT 0.0")
    private Float carbs;

    @Column(name="meals_count",nullable = false,columnDefinition = "Int DEFAULT 3")
    private Integer mealsCount;

    @Column(name = "type_goal", nullable = false)
    private TypeGoal typeGoal;

    @Column(name = "type_activity", nullable = false)
    private TypeActivity typeActivity;

    public GoalEntity(){};

    public GoalEntity(GoalRequest request){
        this.calories = 0.0f;
        this.protein = 0.0f;
        this.fat = 0.0f;
        this.carbs = 0.0f;
        this.mealsCount = request.getMealsCount();
    };
    public GoalResponse asGoal() {
        return new GoalResponse(this);
}
}
