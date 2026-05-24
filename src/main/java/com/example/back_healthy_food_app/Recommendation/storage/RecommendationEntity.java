package com.example.back_healthy_food_app.Recommendation.storage;

import com.example.back_healthy_food_app.Recommendation.dto.RecommendationRequest;
import com.example.back_healthy_food_app.Recommendation.dto.RecommendationResponse;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recommendation")
@Data
public class RecommendationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="text",nullable = false)
    private String text;

    public RecommendationEntity(){}

    public RecommendationEntity(RecommendationRequest Request){
        this.title = Request.getTitle();
        this.text = Request.getText();
    }
    public RecommendationResponse asRecommendation() {
        return new RecommendationResponse(this);
    }
}
