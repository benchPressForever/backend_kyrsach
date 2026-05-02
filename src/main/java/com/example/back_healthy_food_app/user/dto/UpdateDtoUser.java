package com.example.back_healthy_food_app.user.dto;

import com.example.back_healthy_food_app.auth.dto.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDtoUser {
    @Size(min = 3, max = 50, message = "Имя должно содержать от 3 до 50 символов")
    private String name;

    @Email(message = "Введите корректный email адрес")
    @Size(max = 100, message = "Email не может быть длиннее 100 символов")
    private String email;

    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;

    private Gender gender;

    @Past(message = "Дата рождения не может быть в будущем")
    private LocalDate birthDate;
}
