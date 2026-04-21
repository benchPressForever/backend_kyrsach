package com.example.back_healthy_food_app.Recommendation.service;

import com.example.back_healthy_food_app.Recommendation.dto.RecommendationRequest;
import com.example.back_healthy_food_app.Recommendation.dto.RecommendationResponse;

import java.util.List;

public interface IRecommendationService {
    void delete(String id);

    RecommendationResponse getById(String id);

    RecommendationResponse update(String id, RecommendationRequest dto);

    List<RecommendationResponse> getAll();

    RecommendationResponse create(RecommendationRequest dto);
}
