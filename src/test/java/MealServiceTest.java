package com.example.back_healthy_food_app.meal.service;

import com.example.back_healthy_food_app.daily_stat.service.DailyStatService;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import com.example.back_healthy_food_app.errors.MealFoodNotFoundException;
import com.example.back_healthy_food_app.errors.MealNotFoundException;
import com.example.back_healthy_food_app.meal.dto.MealRequest;
import com.example.back_healthy_food_app.meal.dto.MealResponse;
import com.example.back_healthy_food_app.meal.dto.UpdateDtoMeal;
import com.example.back_healthy_food_app.meal.storage.MealEntity;
import com.example.back_healthy_food_app.meal.storage.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealRepository repository;

    @Mock
    private DailyStatService dailyStatService;

    @InjectMocks
    private MealService mealService;

    private String testMealId;
    private String testDailyId;
    private MealRequest testMealRequest;
    private MealEntity testMealEntity;
    private DailyStatEntity testDailyStatEntity;
    private UpdateDtoMeal testUpdateDto;

    @BeforeEach
    void setUp() {
        testMealId = "meal123";
        testDailyId = "daily456";

        testDailyStatEntity = new DailyStatEntity();
        testDailyStatEntity.setId(testDailyId);

        testMealRequest = new MealRequest();
        testMealRequest.setDailyId(testDailyId);
        testMealRequest.setName("Breakfast");
        testMealRequest.setNotes("Healthy breakfast");

        testMealEntity = new MealEntity(testMealRequest, testDailyStatEntity);
        testMealEntity.setId(testMealId);

        testUpdateDto = new UpdateDtoMeal();
        testUpdateDto.setName("Updated Breakfast");
        testUpdateDto.setNotes("Updated notes");
    }

    @Test
    void insert_ShouldSaveAndReturnMealResponse_WhenValidRequest() {
        when(dailyStatService.getEntityById(testDailyId)).thenReturn(testDailyStatEntity);
        when(repository.save(any(MealEntity.class))).thenReturn(testMealEntity);

        MealResponse result = mealService.insert(testMealRequest);

        assertNotNull(result);
        assertEquals(testMealId, result.getId());
        assertEquals("Breakfast", result.getName());
        assertEquals("Healthy breakfast", result.getNotes());

        verify(dailyStatService).getEntityById(testDailyId);
        verify(repository).save(any(MealEntity.class));
    }

    @Test
    void insert_ShouldCreateMealEntityWithCorrectFields() {
        when(dailyStatService.getEntityById(testDailyId)).thenReturn(testDailyStatEntity);
        when(repository.save(any(MealEntity.class))).thenAnswer(invocation -> {
            MealEntity savedEntity = invocation.getArgument(0);
            savedEntity.setId(testMealId);
            return savedEntity;
        });

        MealResponse result = mealService.insert(testMealRequest);

        assertNotNull(result);
        assertEquals(testMealId, result.getId());
        assertEquals(testMealRequest.getName(), result.getName());
        assertEquals(testMealRequest.getNotes(), result.getNotes());

        verify(repository).save(any(MealEntity.class));
    }

    @Test
    void insert_ShouldThrowException_WhenDailyStatNotFound() {
        when(dailyStatService.getEntityById(testDailyId)).thenThrow(new RuntimeException("DailyStat not found"));

        assertThrows(RuntimeException.class, () -> mealService.insert(testMealRequest));
        
        verify(dailyStatService).getEntityById(testDailyId);
        verify(repository, never()).save(any());
    }

    @Test
    void delete_ShouldDelete_WhenExists() {
        when(repository.existsById(testMealId)).thenReturn(true);
        doNothing().when(repository).deleteById(testMealId);

        assertDoesNotThrow(() -> mealService.delete(testMealId));
        
        verify(repository).existsById(testMealId);
        verify(repository).deleteById(testMealId);
    }

    @Test
    void delete_ShouldThrowException_WhenNotExists() {
        when(repository.existsById(testMealId)).thenReturn(false);

        assertThrows(MealFoodNotFoundException.class, () -> mealService.delete(testMealId));
        
        verify(repository).existsById(testMealId);
        verify(repository, never()).deleteById(anyString());
    }

    @Test
    void update_ShouldUpdateAndReturnMealResponse_WhenExists() {
        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));
        when(repository.save(any(MealEntity.class))).thenReturn(testMealEntity);

        MealResponse result = mealService.update(testMealId, testUpdateDto);

        assertNotNull(result);
        assertEquals(testMealId, result.getId());
        assertEquals("Updated Breakfast", result.getName());
        assertEquals("Updated notes", result.getNotes());

        verify(repository).findById(testMealId);
        verify(repository).save(testMealEntity);
    }

    @Test
    void update_ShouldUpdateFieldsCorrectly() {
        UpdateDtoMeal updateDto = new UpdateDtoMeal();
        updateDto.setName("Lunch");
        updateDto.setNotes("Light lunch");

        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));
        when(repository.save(testMealEntity)).thenReturn(testMealEntity);

        mealService.update(testMealId, updateDto);

        assertEquals("Lunch", testMealEntity.getName());
        assertEquals("Light lunch", testMealEntity.getNotes());
        
        verify(repository).save(testMealEntity);
    }

    @Test
    void update_ShouldThrowException_WhenMealNotFound() {
        when(repository.findById(testMealId)).thenReturn(Optional.empty());

        assertThrows(MealNotFoundException.class, () -> mealService.update(testMealId, testUpdateDto));
        
        verify(repository).findById(testMealId);
        verify(repository, never()).save(any());
    }

    @Test
    void update_ShouldSetNullWhenOnlyNameUpdated() {
        UpdateDtoMeal updateDto = new UpdateDtoMeal();
        updateDto.setName("New Name");

        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));
        when(repository.save(testMealEntity)).thenReturn(testMealEntity);

        mealService.update(testMealId, updateDto);

        assertEquals("New Name", testMealEntity.getName());
        assertNull(testMealEntity.getNotes());
        
        verify(repository).save(testMealEntity);
    }

    @Test
    void update_ShouldSetNullWhenOnlyNotesUpdated() {
        UpdateDtoMeal updateDto = new UpdateDtoMeal();
        updateDto.setNotes("New notes only");

        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));
        when(repository.save(testMealEntity)).thenReturn(testMealEntity);

        mealService.update(testMealId, updateDto);

        assertNull(testMealEntity.getName());
        assertEquals("New notes only", testMealEntity.getNotes());
        
        verify(repository).save(testMealEntity);
    }

    @Test
    void get_ShouldReturnMealResponse_WhenExists() {
        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));

        MealResponse result = mealService.get(testMealId);

        assertNotNull(result);
        assertEquals(testMealId, result.getId());
        assertEquals("Breakfast", result.getName());
        assertEquals("Healthy breakfast", result.getNotes());

        verify(repository).findById(testMealId);
    }

    @Test
    void get_ShouldThrowException_WhenNotFound() {
        when(repository.findById(testMealId)).thenReturn(Optional.empty());

        assertThrows(MealNotFoundException.class, () -> mealService.get(testMealId));
        
        verify(repository).findById(testMealId);
    }

    @Test
    void getEntityById_ShouldReturnMealEntity_WhenExists() {
        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));

        MealEntity result = mealService.getEntityById(testMealId);

        assertNotNull(result);
        assertEquals(testMealId, result.getId());
        assertEquals("Breakfast", result.getName());

        verify(repository).findById(testMealId);
    }

    @Test
    void getEntityById_ShouldThrowException_WhenNotFound() {
        when(repository.findById(testMealId)).thenReturn(Optional.empty());

        assertThrows(MealNotFoundException.class, () -> mealService.getEntityById(testMealId));
        
        verify(repository).findById(testMealId);
    }

    @Test
    void insert_ShouldHandleNullNotes() {
        MealRequest requestWithNullNotes = new MealRequest();
        requestWithNullNotes.setDailyId(testDailyId);
        requestWithNullNotes.setName("Snack");
        requestWithNullNotes.setNotes(null);

        MealEntity entityWithNullNotes = new MealEntity(requestWithNullNotes, testDailyStatEntity);
        entityWithNullNotes.setId("snack123");

        when(dailyStatService.getEntityById(testDailyId)).thenReturn(testDailyStatEntity);
        when(repository.save(any(MealEntity.class))).thenReturn(entityWithNullNotes);

        MealResponse result = mealService.insert(requestWithNullNotes);

        assertNotNull(result);
        assertNull(result.getNotes());
        
        verify(repository).save(any(MealEntity.class));
    }

    @Test
    void update_ShouldHandleNullValues() {
        UpdateDtoMeal updateDto = new UpdateDtoMeal();
        updateDto.setName(null);
        updateDto.setNotes(null);

        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));
        when(repository.save(testMealEntity)).thenReturn(testMealEntity);

        MealResponse result = mealService.update(testMealId, updateDto);

        assertNotNull(result);
        assertNull(testMealEntity.getName());
        assertNull(testMealEntity.getNotes());
        
        verify(repository).save(testMealEntity);
    }

    @Test
    void delete_ShouldHandleMultipleDeletes() {
        String secondMealId = "meal456";
        
        when(repository.existsById(testMealId)).thenReturn(true);
        when(repository.existsById(secondMealId)).thenReturn(true);
        doNothing().when(repository).deleteById(anyString());

        assertDoesNotThrow(() -> mealService.delete(testMealId));
        assertDoesNotThrow(() -> mealService.delete(secondMealId));
        
        verify(repository, times(2)).deleteById(anyString());
    }

    @Test
    void get_ShouldReturnDifferentMeals() {
        String secondMealId = "meal789";
        MealEntity secondMealEntity = new MealEntity(testMealRequest, testDailyStatEntity);
        secondMealEntity.setId(secondMealId);
        secondMealEntity.setName("Dinner");

        when(repository.findById(testMealId)).thenReturn(Optional.of(testMealEntity));
        when(repository.findById(secondMealId)).thenReturn(Optional.of(secondMealEntity));

        MealResponse firstResult = mealService.get(testMealId);
        MealResponse secondResult = mealService.get(secondMealId);

        assertEquals("Breakfast", firstResult.getName());
        assertEquals("Dinner", secondResult.getName());
        
        verify(repository).findById(testMealId);
        verify(repository).findById(secondMealId);
    }
}
