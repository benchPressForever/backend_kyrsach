package com.example.back_healthy_food_app.daily_stat.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyRepository extends JpaRepository<DailyStatEntity,String> {

    Optional<DailyStatEntity> findByDateAndUserId(String userId, LocalDate date);

}
