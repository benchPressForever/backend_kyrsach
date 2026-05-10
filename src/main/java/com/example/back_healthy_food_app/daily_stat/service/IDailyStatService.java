package com.example.back_healthy_food_app.daily_stat.service;

import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.daily_stat.dto.GetDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.dto.UpdateDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface IDailyStatService {

    DailyStatResponse getByDate(String userId, GetDtoDailyStat dto);

    DailyStatResponse getById(String id);

    DailyStatEntity getEntityById(String id);

    DailyStatResponse create(DailyStatRequest request,String userId);

    DailyStatResponse update(String id, UpdateDtoDailyStat dto);

    void delete(String id);

}
