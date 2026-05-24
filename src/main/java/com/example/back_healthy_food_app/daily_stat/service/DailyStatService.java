package com.example.back_healthy_food_app.daily_stat.service;

import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.daily_stat.dto.GetDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.dto.UpdateDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.storage.DailyRepository;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import com.example.back_healthy_food_app.errors.DailyAlreadyExistsException;
import com.example.back_healthy_food_app.errors.DailyStatNotFoundException;
import com.example.back_healthy_food_app.errors.MealNotFoundException;
import com.example.back_healthy_food_app.meal.service.MealService;
import com.example.back_healthy_food_app.user.service.UserService;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import com.example.back_healthy_food_app.user.storage.UserRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

import java.time.LocalDate;

@Service
public class DailyStatService implements IDailyStatService {

    private final DailyRepository repository;
    private final UserService userService;

    public DailyStatService(DailyRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public DailyStatResponse getByDate(String userId, GetDtoDailyStat dto) {
        return repository
                .findAllByUserIdAndDate(userId,dto.getDate())
                .map(DailyStatEntity::asDailyStat)
                .orElseThrow(() -> new DailyStatNotFoundException(userId));
    }

    @Override
    public DailyStatResponse getById(String id) {
        return repository.findById(id).map(DailyStatEntity::asDailyStat)
                .orElseThrow(() ->  new DailyStatNotFoundException(id));
    }

    @Override
    public DailyStatEntity getEntityById(String id) {
        return repository.findById(id).orElseThrow(() ->  new DailyStatNotFoundException(id));
    }

    @Override
    public DailyStatResponse create(DailyStatRequest request,String userId) {

        if(repository.existsByUserIdAndDate(userId,request.getDate())){
            throw new DailyAlreadyExistsException(request.getDate());
        }

        UserEntity user = userService.getEntityById(userId);

        DailyStatEntity dailyEntity = new DailyStatEntity(request,user);

        return repository.save(dailyEntity).asDailyStat();
    }

    @Override
    public DailyStatResponse update(String id, UpdateDtoDailyStat dto) {
            DailyStatEntity dailyEntity = repository.findById(id)
                    .orElseThrow(() -> new DailyStatNotFoundException(id));

            if(dto.getHeight() != null){
                dailyEntity.setHeight(dto.getHeight());
            }
            if(dto.getWeight() != null){
                dailyEntity.setWeight(dto.getWeight());
            }
            if(dto.getMealsCount() != null){
                dailyEntity.setMealsCount(dto.getMealsCount());
            }

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
