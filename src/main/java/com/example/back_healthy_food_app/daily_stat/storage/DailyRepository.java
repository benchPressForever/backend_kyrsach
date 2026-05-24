package com.example.back_healthy_food_app.daily_stat.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyRepository extends JpaRepository<DailyStatEntity,String> {

    Optional<DailyStatEntity> findAllByUserIdAndDate(String userId, LocalDate date);

    boolean existsByUserIdAndDate(String userId, LocalDate date);
}
