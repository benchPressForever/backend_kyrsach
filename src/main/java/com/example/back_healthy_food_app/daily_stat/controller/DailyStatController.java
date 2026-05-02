package com.example.back_healthy_food_app.daily_stat.controller;


import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.daily_stat.dto.UpdateDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.service.DailyStatService;
import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal.dto.UpdateDtoMeal;
import com.example.back_healthy_food_app.user.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/daily")
public class DailyStatController {

    private final DailyStatService service;

    public DailyStatController(DailyStatService service){
        this.service = service;
    }

    /*@GetMapping
    public MealResponse getByDate(
            @RequestParam LocalDate date,
            @RequestParam String userId){
        return service.getByDate(userId,date);
    }*/

    @GetMapping("{id}")
    public DailyStatResponse getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DailyStatResponse create(@Valid @RequestBody DailyStatRequest request
                                   ,@AuthenticationPrincipal UserDetailsImpl currentUser){
        return service.create(request,currentUser.getId());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DailyStatResponse update(@PathVariable String id,@Valid @RequestBody UpdateDtoDailyStat dto){
        return service.update(id,dto);
    }
}
