package com.example.back_healthy_food_app.auth.dto;

import com.example.back_healthy_food_app.user.dto.UserResponse;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthResponse {
    private String accessToken;
    private UserResponse user;

    public AuthResponse(UserEntity user,String accessToken) {
        this.accessToken = accessToken;
        this.user = new UserResponse(user);
    }
}
