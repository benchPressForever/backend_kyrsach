package com.example.back_healthy_food_app.goal.controller;

import com.example.back_healthy_food_app.goal.dto.GoalRequest;
import com.example.back_healthy_food_app.goal.dto.GoalResponse;
import com.example.back_healthy_food_app.goal.dto.UpdateDtoGoal;
import com.example.back_healthy_food_app.goal.service.GoalService;
import com.example.back_healthy_food_app.user.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/goal")
public class GoalController {
    private final GoalService service;

    public GoalController(GoalService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GoalResponse get(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        return service.get(currentUser.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoalResponse create(@Valid @RequestBody GoalRequest  dto,
                               @AuthenticationPrincipal UserDetailsImpl currentUser) {
        return service.create(currentUser.getId(), dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        service.delete(currentUser.getId());
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public GoalResponse update(@Valid @RequestBody UpdateDtoGoal dto,
                               @AuthenticationPrincipal UserDetailsImpl currentUser) {
        return service.update(currentUser.getId(), dto);
    }

    @PatchMapping("/recalculate")
    @ResponseStatus(HttpStatus.OK)
    public GoalResponse recalculate(@Valid @RequestBody GoalRequest request,
                                    @AuthenticationPrincipal UserDetailsImpl currentUser) {
        return service.recalculate(currentUser.getId(), request);
    }
}