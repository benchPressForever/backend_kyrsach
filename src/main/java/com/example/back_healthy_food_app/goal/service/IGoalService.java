package com.example.back_healthy_food_app.goal.service;

import com.example.back_healthy_food_app.goal.dto.GoalRequest;
import com.example.back_healthy_food_app.goal.dto.UpdateDtoGoal;
import com.example.back_healthy_food_app.goal.dto.GoalResponse;

public interface IGoalService {
    GoalResponse create(String id,GoalRequest  dto);

    void delete(String id);

    GoalResponse get(String id);

    GoalResponse recalculate(String id, GoalRequest dto);


    GoalResponse update(String id, UpdateDtoGoal dto);
}