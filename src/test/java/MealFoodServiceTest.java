package com.example.back_healthy_food_app.meal_food.service;

import com.example.back_healthy_food_app.daily_stat.storage.DailyRepository;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import com.example.back_healthy_food_app.errors.MealFoodNotFoundException;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.food.storage.FoodRepository;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import com.example.back_healthy_food_app.meal.storage.MealRepository;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodRequest;
import com.example.back_healthy_food_app.meal_food.dto.MealFoodResponse;
import com.example.back_healthy_food_app.meal_food.dto.UpdateDtoMealFood;
import com.example.back_healthy_food_app.meal_food.storage.MealFoodRepository;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import com.example.back_healthy_food_app.user.storage.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Transactional
class MealFoodServiceTest {

    @Autowired
    private MealFoodService mealFoodService;

    @Autowired
    private MealFoodRepository mealFoodRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private UserRepository userRepository;

    private String testFoodId;
    private String testMealId;

    @BeforeEach
    void setUp() {
        // Очищаем БД в правильном порядке
        mealFoodRepository.deleteAll();
        mealRepository.deleteAll();
        dailyRepository.deleteAll();
        foodRepository.deleteAll();
        userRepository.deleteAll();

        // 1. Создаём User со всеми обязательными полями
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setName("Test User");  // Добавляем name (обязательное поле!)
        // Если есть другие обязательные поля, добавьте их
        user = userRepository.save(user);

        // 2. Создаём DailyStat с User
        DailyStatEntity dailyStat = new DailyStatEntity();
        dailyStat.setUser(user);
        dailyStat.setDate(LocalDate.now());
        dailyStat = dailyRepository.save(dailyStat);

        // 3. Создаём Food
        FoodDBEntity food = new FoodDBEntity();
        food.setName("Test Food");
        food.setCaloriesPer100(100.0);
        food.setProteinPer100(10.0);
        food.setFatPer100(5.0);
        food.setCarbsPer100(20.0);
        food = foodRepository.save(food);
        testFoodId = food.getId();

        // 4. Создаём Meal с DailyStat
        MealEntity meal = new MealEntity();
        meal.setName("Test Meal");
        meal.setNotes("Test notes");
        meal.setDaily(dailyStat);
        meal = mealRepository.save(meal);
        testMealId = meal.getId();
    }

    @Test
    void save_ShouldSaveMealFood_WhenValidRequest() {
        MealFoodRequest request = new MealFoodRequest();
        request.setFoodId(testFoodId);
        request.setMealId(testMealId);
        request.setServingSize(150.0f);

        MealFoodResponse response = mealFoodService.save(request);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(150.0f, response.getServingSize());
        assertEquals(testFoodId, response.getFoodId());
    }

    @Test
    void get_ShouldReturnMealFood_WhenExists() {
        MealFoodRequest request = new MealFoodRequest();
        request.setFoodId(testFoodId);
        request.setMealId(testMealId);
        request.setServingSize(100.0f);
        MealFoodResponse saved = mealFoodService.save(request);

        MealFoodResponse found = mealFoodService.get(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals(100.0f, found.getServingSize());
    }

    @Test
    void get_ShouldThrowNotFoundException_WhenNotExists() {
        assertThrows(MealFoodNotFoundException.class,
                () -> mealFoodService.get("non-existent-id"));
    }

    @Test
    void update_ShouldUpdateServingSize_WhenExists() {
        MealFoodRequest request = new MealFoodRequest();
        request.setFoodId(testFoodId);
        request.setMealId(testMealId);
        request.setServingSize(100.0f);
        MealFoodResponse saved = mealFoodService.save(request);

        UpdateDtoMealFood updateDto = new UpdateDtoMealFood();
        updateDto.setServingSize(200.0f);

        MealFoodResponse updated = mealFoodService.update(saved.getId(), updateDto);

        assertEquals(200.0f, updated.getServingSize());
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenNotExists() {
        UpdateDtoMealFood updateDto = new UpdateDtoMealFood();
        updateDto.setServingSize(200.0f);

        assertThrows(MealFoodNotFoundException.class,
                () -> mealFoodService.update("non-existent-id", updateDto));
    }

    @Test
    void delete_ShouldDeleteMealFood_WhenExists() {
        MealFoodRequest request = new MealFoodRequest();
        request.setFoodId(testFoodId);
        request.setMealId(testMealId);
        request.setServingSize(100.0f);
        MealFoodResponse saved = mealFoodService.save(request);

        mealFoodService.delete(saved.getId());

        assertThrows(MealFoodNotFoundException.class,
                () -> mealFoodService.get(saved.getId()));
    }

    @Test
    void delete_ShouldThrowNotFoundException_WhenNotExists() {
        assertThrows(MealFoodNotFoundException.class,
                () -> mealFoodService.delete("non-existent-id"));
    }

    @Test
    void save_ShouldCalculateNutrition_WhenServingSizeIs100() {
        MealFoodRequest request = new MealFoodRequest();
        request.setFoodId(testFoodId);
        request.setMealId(testMealId);
        request.setServingSize(100.0f);

        MealFoodResponse response = mealFoodService.save(request);

        assertEquals(100.0f, response.getCalories());
        assertEquals(10.0f, response.getProtein());
        assertEquals(5.0f, response.getFats());
        assertEquals(20.0f, response.getCarbs());
    }

    @Test
    void save_ShouldCalculateNutrition_WhenServingSizeIs50() {
        MealFoodRequest request = new MealFoodRequest();
        request.setFoodId(testFoodId);
        request.setMealId(testMealId);
        request.setServingSize(50.0f);

        MealFoodResponse response = mealFoodService.save(request);

        assertEquals(50.0f, response.getCalories());
        assertEquals(5.0f, response.getProtein());
        assertEquals(2.5f, response.getFats());
        assertEquals(10.0f, response.getCarbs());
    }
}