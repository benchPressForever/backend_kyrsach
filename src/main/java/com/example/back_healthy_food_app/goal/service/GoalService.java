package com.example.back_healthy_food_app.goal.service;

import com.example.back_healthy_food_app.errors.GoalNotFoundException;
import com.example.back_healthy_food_app.errors.UserNotFoundException;
import com.example.back_healthy_food_app.goal.dto.Gender;
import com.example.back_healthy_food_app.goal.dto.GoalRequest;
import com.example.back_healthy_food_app.goal.dto.GoalResponse;
import com.example.back_healthy_food_app.goal.dto.UpdateDtoGoal;
import com.example.back_healthy_food_app.goal.storage.GoalEntity;
import com.example.back_healthy_food_app.goal.storage.GoalRepository;
import com.example.back_healthy_food_app.user.service.UserService;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import com.example.back_healthy_food_app.user.storage.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoalService implements  IGoalService {
    private final GoalRepository repository;
    private final UserRepository userService;
    public GoalService(GoalRepository repository, UserRepository userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public GoalResponse create(String userId, GoalRequest  dto) {
        UserEntity user = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        GoalEntity goal = new GoalEntity(dto,user);
        repository.save(goal).asGoal();

        return this.recalculate(userId,dto);
    }

    @Override
    public void delete(String userId) {
        GoalEntity goal = repository.getByUserId(userId)
                .orElseThrow(() -> new GoalNotFoundException(userId));
        UserEntity user = goal.getUser();
        if (user != null) {
            user.setGoal(null);
        }
        repository.deleteById(goal.getId());
    }

    @Override
    public GoalResponse get(String userId) {
        return repository.getByUserId(userId).
                map(GoalEntity::asGoal).
                orElseThrow(() -> new GoalNotFoundException(userId));
    }

    public GoalResponse recalculate(String userId, GoalRequest dto) {
        GoalEntity goal = repository.getByUserId(userId)
                .orElseThrow(() -> new GoalNotFoundException(userId));
        Map<String, Object> calculated = calculateBJU(dto);

        goal.setCalories((Float) calculated.get("calories"));
        goal.setProtein((Float) calculated.get("protein"));
        goal.setFat((Float) calculated.get("fat"));
        goal.setCarbs((Float) calculated.get("carbs"));
        goal.setTypeGoal(dto.getTypeGoal());
        goal.setTypeActivity(dto.getTypeActivity());
        goal.setMealsCount(dto.getMealsCount());

        return repository.save(goal).asGoal();
    }

    private Map<String, Object> calculateBJU(GoalRequest dto) {
        Map<String, Object> result = new HashMap<>();
        // Расчет BMR по Миффлину-Сан Жеора
        double weight = dto.getWeight();
        double height = dto.getHeight();

        double bmr = (10 * weight) + (6.25 * height) - (5 * dto.getAge());
        bmr += (dto.getGender() == Gender.MALE) ? 5 : -161;

        // Расчет калорий с учетом активности
        double calories = bmr * GoalRequest.getActivityMultiplier(dto.getTypeActivity());

        // Корректировка калорий под цель
        Map<String, Double> config = GoalRequest.getGoalConfigAll(dto.getTypeGoal());
        calories = calories * config.get("calorieAdjustment");

        // Расчет БЖУ в граммах
        double protein = weight * config.get("proteinMultiplier");
        double fat = (calories * config.get("fatRatio")) / 9;     // 1г жира = 9 ккал
        double carbs = (calories * config.get("carbRatio")) / 4;  // 1г углеводов = 4 ккал

        //внос значений под вывод
        result.put("calories", (float) Math.round(calories));
        result.put("protein", (float) Math.round(protein));
        result.put("fat", (float) Math.round(fat));
        result.put("carbs", (float) Math.round(carbs));
        result.put("typeGoal", dto.getTypeGoal());
        result.put("typeActivity", dto.getTypeActivity());
        result.put("mealsCount", dto.getMealsCount());
        return result;
    }

    @Override
    public GoalResponse update(String id, UpdateDtoGoal dto) {
        GoalEntity goal = repository.getByUserId(id)
                .orElseThrow(() -> new GoalNotFoundException(id));

        if (dto.getMealsCount() != null) {
            goal.setMealsCount(dto.getMealsCount());
        }
        if (dto.getTypeGoal() != null) {
            goal.setTypeGoal(dto.getTypeGoal());
        }
        if (dto.getTypeActivity() != null) {
            goal.setTypeActivity(dto.getTypeActivity());
        }

        return repository.save(goal).asGoal();
    }
}
