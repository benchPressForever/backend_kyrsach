package com.example.back_healthy_food_app.goal.service;

import com.example.back_healthy_food_app.errors.GoalNotFoundException;
import com.example.back_healthy_food_app.goal.dto.GoalResponse;
import com.example.back_healthy_food_app.goal.dto.UpdateDtoGoal;
import com.example.back_healthy_food_app.goal.storage.GoalEntity;
import com.example.back_healthy_food_app.goal.storage.GoalRepository;
import com.example.back_healthy_food_app.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class GoalService implements  IGoalService {
    private final GoalRepository repository;
    private final UserService userService;
    public GoalService(GoalRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public GoalResponse create(String id, UpdateDtoGoal dto) {
        //UserEntity user = userService.getUserEntity(id);


        return null;
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public GoalResponse get(String id) {
        return repository.findById(id).
                map(GoalEntity::asGoal).
                orElseThrow(() -> new GoalNotFoundException(id));
    }

    @Override
    public GoalResponse recalculate(String id, UpdateDtoGoal dto) {

        return null;
    }

    @Override
    public GoalResponse update(String id, UpdateDtoGoal dto) {
        return null;
    }

}
