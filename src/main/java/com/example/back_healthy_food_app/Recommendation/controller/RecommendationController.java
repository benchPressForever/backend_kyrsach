package com.example.back_healthy_food_app.Recommendation.controller;


import com.example.back_healthy_food_app.Recommendation.dto.RecommendationRequest;
import com.example.back_healthy_food_app.Recommendation.dto.RecommendationResponse;
import com.example.back_healthy_food_app.Recommendation.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/recommendation")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public RecommendationResponse create(@Valid @RequestBody RecommendationRequest dto) {
        return recommendationService.create(dto);
    }

    @GetMapping
    public List<RecommendationResponse> getAll() {
        return recommendationService.getAll();
    }


    @GetMapping("{id}")
    public RecommendationResponse getById(@PathVariable String id){
        return recommendationService.getById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        recommendationService.delete(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecommendationResponse update(@PathVariable String id,@Valid @RequestBody RecommendationRequest dto){
        return recommendationService.update(id,dto);
    }
}
