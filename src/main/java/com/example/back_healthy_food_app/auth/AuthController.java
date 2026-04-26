package com.example.back_healthy_food_app.auth;


import com.example.back_healthy_food_app.auth.dto.AuthResponse;
import com.example.back_healthy_food_app.auth.dto.SigninRequest;
import com.example.back_healthy_food_app.auth.dto.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    AuthResponse signup(@Valid @RequestBody SignupRequest request) {
        return service.signup(request);
    }

    @PostMapping("/signin")
    AuthResponse signup(@Valid @RequestBody SigninRequest request) {
        return service.signin(request);
    }
}
