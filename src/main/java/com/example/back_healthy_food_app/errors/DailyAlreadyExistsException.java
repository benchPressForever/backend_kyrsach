package com.example.back_healthy_food_app.errors;

import java.time.LocalDate;

public class DailyAlreadyExistsException extends RuntimeException {
    public DailyAlreadyExistsException(LocalDate date) {
        super("Статистика за день " + date + " уже существует!");
    }
}
