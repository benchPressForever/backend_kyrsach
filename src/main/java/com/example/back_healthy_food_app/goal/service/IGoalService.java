package com.example.back_healthy_food_app.goal.service;

import com.example.back_healthy_food_app.goal.dto.UpdateDtoGoal;
import com.example.back_healthy_food_app.goal.dto.GoalResponse;

public interface IGoalService {
    GoalResponse create(String id,UpdateDtoGoal dto);//??

    void delete(String id);

    GoalResponse get(String id);

    GoalResponse recalculate(String id,UpdateDtoGoal dto);//??

    GoalResponse update(String id,UpdateDtoGoal dto);//??
}