package com.example.back_healthy_food_app.auth.dto;


import com.example.back_healthy_food_app.auth.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest {

    @NotBlank(message = "Имя пользователя обязательно")
    @Size(min = 3, max = 30, message = "Имя пользователя: от 3 до 30 символов")
    private String name;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Введите корректный email")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, max = 50, message = "Пароль: от 6 до 50 символов")
    private String password;

    @NotNull(message = "Gender обязателен")
    private Gender gender;

    @Past(message = "Дата рождения не может быть в будущем")
    private LocalDate birthDate;
}
