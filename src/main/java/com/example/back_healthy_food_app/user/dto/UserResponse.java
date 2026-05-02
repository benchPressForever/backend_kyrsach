package com.example.back_healthy_food_app.user.dto;

import com.example.back_healthy_food_app.auth.dto.Gender;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Gender gender;

    public UserResponse(UserEntity user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        birthDate = user.getBirthDate();
        gender = user.getGender();
    }
}
