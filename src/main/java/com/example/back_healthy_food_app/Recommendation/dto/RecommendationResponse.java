package com.example.back_healthy_food_app.Recommendation.dto;

import com.example.back_healthy_food_app.Recommendation.storage.RecommendationEntity;
import lombok.Data;

@Data
public class RecommendationResponse {
    private String id;
    private String title;
    private String text;
    public RecommendationResponse(RecommendationEntity recommendationEntity) {
        this.id = recommendationEntity.getId();
        this.text = recommendationEntity.getText();
        this.title = recommendationEntity.getTitle();
    }
}
