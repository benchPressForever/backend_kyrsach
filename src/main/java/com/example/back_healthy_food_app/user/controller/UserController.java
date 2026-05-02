package com.example.back_healthy_food_app.user.controller;


import com.example.back_healthy_food_app.user.UserDetailsImpl;
import com.example.back_healthy_food_app.user.dto.UpdateDtoUser;
import com.example.back_healthy_food_app.user.dto.UserResponse;
import com.example.back_healthy_food_app.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public UserResponse getByID(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        return service.getById(currentUser.getId());
    }

    @DeleteMapping
    public void deleteByID(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        service.deleteById(currentUser.getId());
    }

    @PatchMapping
    public UserResponse updateByID(@Valid @RequestBody UpdateDtoUser user,
                                   @AuthenticationPrincipal UserDetailsImpl currentUser) {
        return service.update(user, currentUser.getId());
    }

}
