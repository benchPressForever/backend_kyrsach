package com.example.back_healthy_food_app.daily_stat.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyStatRequest {

    @NotBlank(message = "userId не может быть пустым")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "userId должен быть в формате UUID")
    private String userId;

    @NotNull(message = "Вес не может быть null")
    @DecimalMin(value = "20.0", message = "Вес должен быть не менее 20 кг")
    @DecimalMax(value = "500.0", message = "Вес должен быть не более 500 кг")
    private Float weight;

    @NotNull(message = "Рост не может быть null")
    @Min(value = 50, message = "Рост должен быть не менее 50 см")
    @Max(value = 300, message = "Рост должен быть не более 300 см")
    private Integer height;

    @NotNull(message = "Дата не может быть null")
    @PastOrPresent(message = "Дата не может быть в будущем")
    private LocalDate date;

    @NotNull(message = "Количество приемов пищи не может быть null")
    @Min(value = 1, message = "Количество приемов пищи должно быть не менее 1")
    @Max(value = 10, message = "Количество приемов пищи должно быть не более 10")
    private Integer mealsCount;

    DailyStatRequest(){}

    DailyStatRequest(Float weight, Integer height, LocalDate date, Integer mealsCount,String userId) {
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.mealsCount = mealsCount;
        this.userId = userId;
    }
}
