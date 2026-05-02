package com.example.back_healthy_food_app.meal.controller;

import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal.dto.UpdateDtoMeal;
import com.example.back_healthy_food_app.meal.service.MealService;
import com.example.back_healthy_food_app.user.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/meal")
public class MealController {
    private final MealService service;

    public MealController(MealService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public MealResponse getById(@PathVariable String id){
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealResponse create(@Valid @RequestBody MealRequest request
                              ,@AuthenticationPrincipal UserDetailsImpl currentUser){
        return service.insert(request,currentUser.getId());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MealResponse update(@PathVariable String id,@Valid @RequestBody UpdateDtoMeal dto){
        return service.update(id,dto);
    }
}
