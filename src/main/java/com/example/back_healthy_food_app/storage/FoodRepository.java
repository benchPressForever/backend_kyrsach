package com.example.back_healthy_food_app.storage;


import com.example.back_healthy_food_app.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.back_healthy_food_app.storage.FoodDBEntity;

@Repository
public interface FoodRepository extends JpaRepository<FoodDBEntity,Integer> {

    // Поиск по имени с пагинацией
    Page<FoodDBEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
