package com.example.back_healthy_food_app.daily_stat.service;

import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.daily_stat.dto.UpdateDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.storage.DailyRepository;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import com.example.back_healthy_food_app.errors.DailyStatNotFoundException;
import com.example.back_healthy_food_app.errors.MealNotFoundException;
import com.example.back_healthy_food_app.user.service.UserService;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import com.example.back_healthy_food_app.user.storage.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyStatService implements IDailyStatService {

    private final DailyRepository repository;
    private final UserService userService;

    public DailyStatService(DailyRepository repository, UserRepository userRepository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }


    @Override
    public DailyStatResponse getByDate(String userId,LocalDate date) {
        return null;
    }

    @Override
    public DailyStatResponse getById(String id) {
        return repository.findById(id).map(DailyStatEntity::asDailyStat)
                .orElseThrow(() ->  new DailyStatNotFoundException(id));
    }

    @Override
    public DailyStatResponse create(DailyStatRequest request,String userId) {
        UserEntity user = userService.getEntityById(userId);

        DailyStatEntity dailyEntity = new DailyStatEntity(request,user);

        return repository.save(dailyEntity).asDailyStat();
    }

    @Override
    public DailyStatResponse update(String id, UpdateDtoDailyStat dto) {
            DailyStatEntity dailyEntity = repository.findById(id)
                    .orElseThrow(() -> new DailyStatNotFoundException(id));

            dailyEntity.setWeight(dto.getWeight());
            dailyEntity.setHeight(dto.getHeight());
            dailyEntity.setMealsCount(dto.getMealsCount());

            return repository.save(dailyEntity).asDailyStat();
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DailyStatNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
