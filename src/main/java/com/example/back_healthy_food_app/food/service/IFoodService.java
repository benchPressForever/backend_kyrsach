package com.example.back_healthy_food_app.food.service;

import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.food.dto.FoodGetDto;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import jakarta.persistence.Entity;

import java.util.List;

// FoodStorage - интерфейс хранилища стран
public interface IFoodService {

    //searchByName - получение по имени с пагинацией
    List<Food> searchAllByName(FoodGetDto dto);

    //getById - получение по id
    Food getById(String id);

    //getById - получение Entity сущности по id
    FoodDBEntity getEntityById(String id);

    //insert - добавление продукта
    Food insert(Food food);

    //update - обновление по id
    Food update(String id,Food food);

    //delete - удаление по id
    void delete(String id);

    //createMany - создание списка продуктов
    List<Food> createMany(List<Food> foods);
}
