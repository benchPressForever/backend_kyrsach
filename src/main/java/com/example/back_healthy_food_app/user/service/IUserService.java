package com.example.back_healthy_food_app.user.service;

import com.example.back_healthy_food_app.user.dto.UpdateDtoUser;
import com.example.back_healthy_food_app.user.dto.UserResponse;
import com.example.back_healthy_food_app.user.storage.UserEntity;

public interface IUserService {

    UserResponse getById(String id);

    UserEntity getEntityById(String id);

    void deleteById(String id);

    UserResponse update(UpdateDtoUser dto, String id);
}
