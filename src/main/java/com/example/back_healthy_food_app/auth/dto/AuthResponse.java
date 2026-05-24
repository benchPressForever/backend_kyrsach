package com.example.back_healthy_food_app.auth.dto;

import com.example.back_healthy_food_app.user.storage.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthResponse {
    private String id;
    private String email;
    private String accessToken;
    private Gender gender;
    private String name;
    private LocalDate birthDate;

    public AuthResponse(UserEntity user,String accessToken) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.accessToken = accessToken;
        this.gender = user.getGender();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
    }
}
