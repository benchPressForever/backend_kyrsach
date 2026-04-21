package com.example.back_healthy_food_app.food.storage;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodDBEntity,String> {

    // Поиск по имени с пагинацией
    Page<FoodDBEntity> findByNameContainingIgnoreCaseOrderByNameDesc(String name, Pageable pageable);


}
