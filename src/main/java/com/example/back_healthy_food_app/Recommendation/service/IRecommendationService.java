package com.example.back_healthy_food_app.Recommendation.service;

import com.example.back_healthy_food_app.Recommendation.dto.RecommendationRequest;
import com.example.back_healthy_food_app.Recommendation.dto.RecommendationResponse;
import com.example.back_healthy_food_app.Recommendation.dto.UpdateDtoRecommandation;

import java.util.List;

public interface IRecommendationService {
    void delete(String id);

    RecommendationResponse getById(String id);

    RecommendationResponse update(String id, UpdateDtoRecommandation dto);

    List<RecommendationResponse> getAll();

    RecommendationResponse create(RecommendationRequest dto);
}
