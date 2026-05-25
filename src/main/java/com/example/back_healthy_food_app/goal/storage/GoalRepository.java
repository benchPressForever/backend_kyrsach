package com.example.back_healthy_food_app.goal.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity,String> {
    Optional<GoalEntity> getByUserId(String userId);
}
