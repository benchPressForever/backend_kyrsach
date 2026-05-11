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

    /*
    @Query("SELECT ds FROM DailyStatEntity ds " +
            "LEFT JOIN FETCH ds.meals m " +  // только один FETCH для коллекции
            "WHERE ds.user.id = :userId AND ds.date = :date")
    Optional<DailyStatEntity> findByUserIdAndDateWithAllData(@Param("userId") String userId,
                                                             @Param("date") LocalDate date);*/


}
