package com.example.back_healthy_food_app.user.storage;

import com.example.back_healthy_food_app.auth.dto.Gender;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import com.example.back_healthy_food_app.user.dto.UserResponse;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<MealEntity> meals = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<DailyStatEntity> dailyStats = new ArrayList<>();

    public UserEntity() {}

    public UserEntity(String name, String email, String password, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public UserResponse asUserResponse(){
        return new UserResponse(this);
    }
}
