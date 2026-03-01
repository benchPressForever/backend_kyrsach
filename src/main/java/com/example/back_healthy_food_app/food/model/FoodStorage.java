package com.example.back_healthy_food_app.food.model;

import java.util.List;
import java.util.Optional;

// FoodStorage - интерфейс хранилища стран
public interface FoodStorage {

    //searchByName - получение по имени с пагинацией
    List<Food> searchAllByName(String name, Integer page, Integer limit);

    //getById - получение по id
    Optional<Food> getById(String id);

    //getByName - получение по имени
    Optional<Food> getByName(String name);

    //insert - добавление продукта
    void insert(Food food);

    //update - обновление по id
    void update(String id,Food food);

    //delete - удаление по id
    void delete(String id);

    //createMany - создание списка продуктов
    void createMany(List<Food> foods);
}
