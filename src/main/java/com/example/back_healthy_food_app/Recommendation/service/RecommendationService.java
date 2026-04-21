package com.example.back_healthy_food_app.Recommendation.service;

import com.example.back_healthy_food_app.Recommendation.dto.RecommendationRequest;
import com.example.back_healthy_food_app.Recommendation.dto.RecommendationResponse;
import com.example.back_healthy_food_app.Recommendation.storage.RecommendationEntity;
import com.example.back_healthy_food_app.Recommendation.storage.RecommendationRepository;
import com.example.back_healthy_food_app.errors.RecommendationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService implements IRecommendationService {
    private final RecommendationRepository repository;

    public RecommendationService(RecommendationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new RecommendationNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public RecommendationResponse getById(String id) {//ж - жопа
        return repository.findById(id).
                map(RecommendationEntity::asRecommendation).
                orElseThrow(() -> new RecommendationNotFoundException(id));
    }

    @Override
    public RecommendationResponse update(String id, RecommendationRequest dto) {//ж - жопа
        RecommendationEntity recommendation =  repository.findById(id)
                .orElseThrow(() -> new RecommendationNotFoundException(id));
        recommendation.setText(dto.getText());
        recommendation.setTitle(dto.getTitle());
        return repository.save(recommendation).asRecommendation();
    }

    @Override
    public List<RecommendationResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(RecommendationEntity::asRecommendation)
                .toList();
    }

    @Override
    public RecommendationResponse create(RecommendationRequest dto) {
        RecommendationEntity recommendationEntity = new RecommendationEntity(dto);
        return repository.save(recommendationEntity).asRecommendation();
    }
}
