package com.example.back_healthy_food_app.model;

import java.util.List;

public class FoodScenario {

    private  final FoodStorage storage;

    public FoodScenario(FoodStorage storage){this.storage = storage;}

    public List<Food> GetByName(String name,Integer page,Integer limit){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("name is null or empty");
        }
        if(page == null || page < 1 || limit == null || limit < 1){
            throw new IllegalArgumentException("page or limit is null or empty");
        }
        return storage.searchAllByName(name,page,limit);
    }

}
