package com.example.back_healthy_food_app.daily_stat.service;

import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.daily_stat.dto.UpdateDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.storage.DailyRepository;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import com.example.back_healthy_food_app.errors.DailyStatNotFoundException;
import com.example.back_healthy_food_app.errors.MealNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyStatService implements IDailyStatService {

    private final DailyRepository repository;

    public DailyStatService(DailyRepository repository) {
        this.repository = repository;
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
    public DailyStatResponse create(DailyStatRequest request) {
        DailyStatEntity dailyEntity = new DailyStatEntity(request);

        //реализовать соединение с User

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
