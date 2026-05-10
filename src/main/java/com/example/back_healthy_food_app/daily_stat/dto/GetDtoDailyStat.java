package com.example.back_healthy_food_app.daily_stat.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetDtoDailyStat {

    @NotNull(message = "Дата не может быть null")
    @PastOrPresent(message = "Дата не может быть в будущем")
    private LocalDate date;

    public GetDtoDailyStat() {}

    public GetDtoDailyStat(LocalDate date) {
        this.date = date;
    }
}
