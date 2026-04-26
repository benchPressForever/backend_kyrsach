package com.example.back_healthy_food_app.daily_stat.service;

import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.daily_stat.dto.UpdateDtoDailyStat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface IDailyStatService {

    DailyStatResponse getByDate(String userId,LocalDate date);

    DailyStatResponse getById(String id);

    DailyStatResponse create(DailyStatRequest request);

    DailyStatResponse update(String id, UpdateDtoDailyStat dto);

    void delete(String id);

}
