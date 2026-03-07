package com.example.back_healthy_food_app.food.storage;


import com.example.back_healthy_food_app.food.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodDBEntity,String> {

    // Поиск по имени с пагинацией
    Page<FoodDBEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<FoodDBEntity> findByName(String name);



}
