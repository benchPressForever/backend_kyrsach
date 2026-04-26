package com.example.back_healthy_food_app.auth.dto;

import com.example.back_healthy_food_app.auth.Gender;
import com.example.back_healthy_food_app.user.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthResponse {
    private String id;
    private String email;
    private String token;
    private Gender gender;
    private String name;
    private LocalDate birthDate;

    public AuthResponse(UserEntity user,String token) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.token = token;
        this.gender = user.getGender();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
    }
}
