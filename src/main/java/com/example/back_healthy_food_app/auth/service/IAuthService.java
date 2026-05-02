package com.example.back_healthy_food_app.auth.service;

import com.example.back_healthy_food_app.auth.dto.AuthResponse;
import com.example.back_healthy_food_app.auth.dto.SigninRequest;
import com.example.back_healthy_food_app.auth.dto.SignupRequest;

public interface IAuthService {

    AuthResponse signin(SigninRequest request);

    AuthResponse signup(SignupRequest request);
}
