package com.example.back_healthy_food_app.model;

import java.util.List;

// FoodStorage - интерфейс хранилища стран
public interface FoodStorage {

    //searchByName - получение по имени с пагинацией
    List<Food> searchAllByName(String name,Integer page, Integer limit);

    /*
    //create - создание продукта
    void create(Food food);

    //getById - получение по id
    Food getById(Integer id);

    //update - обновление по id
    void update(Integer id,Food food);

    //delete - удаление по id
    void delete(Integer id);

    //createMany - создание списка продуктов
    void createMany(List<Food> foods);*/
}
