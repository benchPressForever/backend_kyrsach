package com.example.back_healthy_food_app.food.service;

import com.example.back_healthy_food_app.errors.FoodNotFoundException;
import com.example.back_healthy_food_app.food.dto.Food;
import com.example.back_healthy_food_app.food.dto.FoodGetDto;
import com.example.back_healthy_food_app.food.storage.FoodDBEntity;
import com.example.back_healthy_food_app.food.storage.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodRepository repository;

    @InjectMocks
    private FoodService foodService;

    private String testFoodId;
    private Food testFood;
    private FoodDBEntity testFoodEntity;
    private FoodGetDto testGetDto;

    @BeforeEach
    void setUp() {
        testFoodId = "food123";

        testFood = new Food();
        testFood.setId(testFoodId);
        testFood.setName("Apple");
        testFood.setFatPer100(0.3d);
        testFood.setCarbsPer100(14.0d);
        testFood.setProteinPer100(0.5d);
        testFood.setCaloriesPer100(52.0d);

        // ВАЖНО: явно устанавливаем ID при создании сущности
        testFoodEntity = new FoodDBEntity(testFood);
        testFoodEntity.setId(testFoodId);  // Устанавливаем ID явно

        testGetDto = new FoodGetDto();
        testGetDto.setName("Apple");
        testGetDto.setPage(1);
        testGetDto.setLimit(10);
    }

    // ========== ТЕСТЫ ДЛЯ searchAllByName ==========

    @Test
    void searchAllByName_ShouldReturnListOfFoods_WhenMatchesExist() {
        // Arrange
        List<FoodDBEntity> entities = List.of(testFoodEntity);
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<FoodDBEntity> page = new PageImpl<>(entities, pageable, entities.size());

        when(repository.findByNameContainingIgnoreCaseOrderByNameDesc(
                testGetDto.getName(), pageable))
                .thenReturn(page);

        // Act
        List<Food> result = foodService.searchAllByName(testGetDto);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0).getName());
        assertEquals(52.0d, result.get(0).getCaloriesPer100());

        verify(repository).findByNameContainingIgnoreCaseOrderByNameDesc(
                testGetDto.getName(), pageable);
    }

    @Test
    void searchAllByName_ShouldReturnEmptyList_WhenNoMatches() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<FoodDBEntity> emptyPage = new PageImpl<>(List.of(), pageable, 0);

        when(repository.findByNameContainingIgnoreCaseOrderByNameDesc(
                testGetDto.getName(), pageable))
                .thenReturn(emptyPage);

        // Act
        List<Food> result = foodService.searchAllByName(testGetDto);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findByNameContainingIgnoreCaseOrderByNameDesc(
                testGetDto.getName(), pageable);
    }

    @Test
    void searchAllByName_ShouldHandlePageNumberCorrectly() {
        // Arrange
        FoodGetDto dtoWithPage2 = new FoodGetDto();
        dtoWithPage2.setName("Apple");
        dtoWithPage2.setPage(2);
        dtoWithPage2.setLimit(5);

        Pageable expectedPageable = PageRequest.of(1, 5);
        PageImpl<FoodDBEntity> emptyPage = new PageImpl<>(List.of(), expectedPageable, 0);

        when(repository.findByNameContainingIgnoreCaseOrderByNameDesc(
                dtoWithPage2.getName(), expectedPageable))
                .thenReturn(emptyPage);

        // Act
        foodService.searchAllByName(dtoWithPage2);

        // Assert
        verify(repository).findByNameContainingIgnoreCaseOrderByNameDesc(
                dtoWithPage2.getName(), expectedPageable);
    }

    // ========== ТЕСТЫ ДЛЯ insert ==========

    @Test
    void insert_ShouldSaveAndReturnFood() {
        // Arrange
        Food foodToInsert = new Food();
        foodToInsert.setName("Apple");
        foodToInsert.setFatPer100(0.3d);
        foodToInsert.setCarbsPer100(14.0d);
        foodToInsert.setProteinPer100(0.5d);
        foodToInsert.setCaloriesPer100(52.0d);
        // ID не устанавливаем, так как это новый продукт

        FoodDBEntity savedEntity = new FoodDBEntity(foodToInsert);
        savedEntity.setId(testFoodId);  // Мокируем, что БД присвоила ID

        when(repository.save(any(FoodDBEntity.class))).thenReturn(savedEntity);

        // Act
        Food result = foodService.insert(foodToInsert);

        // Assert
        assertNotNull(result);
        assertEquals(testFoodId, result.getId());  // Теперь ID должен быть
        assertEquals("Apple", result.getName());
        assertEquals(52.0d, result.getCaloriesPer100());

        verify(repository).save(any(FoodDBEntity.class));
    }

    @Test
    void insert_ShouldPreserveAllNutritionalValues() {
        // Arrange
        Food customFood = new Food();
        customFood.setName("Banana");
        customFood.setFatPer100(0.4d);
        customFood.setCarbsPer100(23.0d);
        customFood.setProteinPer100(1.1d);
        customFood.setCaloriesPer100(89.0d);

        FoodDBEntity customEntity = new FoodDBEntity(customFood);
        customEntity.setId("banana456");

        when(repository.save(any(FoodDBEntity.class))).thenReturn(customEntity);

        // Act
        Food result = foodService.insert(customFood);

        // Assert
        assertEquals(0.4d, result.getFatPer100());
        assertEquals(23.0d, result.getCarbsPer100());
        assertEquals(1.1d, result.getProteinPer100());
        assertEquals(89.0d, result.getCaloriesPer100());
    }

    // ========== ТЕСТЫ ДЛЯ getById ==========

    @Test
    void getById_ShouldReturnFood_WhenExists() {
        // Arrange
        when(repository.findById(testFoodId)).thenReturn(Optional.of(testFoodEntity));

        // Act
        Food result = foodService.getById(testFoodId);

        // Assert
        assertNotNull(result);
        assertEquals(testFoodId, result.getId());
        assertEquals("Apple", result.getName());

        verify(repository).findById(testFoodId);
    }

    @Test
    void getById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(repository.findById(testFoodId)).thenReturn(Optional.empty());

        // Act & Assert
        FoodNotFoundException exception = assertThrows(
                FoodNotFoundException.class,
                () -> foodService.getById(testFoodId)
        );

        assertTrue(exception.getMessage().contains(testFoodId));
        verify(repository).findById(testFoodId);
    }

    // ========== ТЕСТЫ ДЛЯ getEntityById ==========

    @Test
    void getEntityById_ShouldReturnEntity_WhenExists() {
        // Arrange
        when(repository.findById(testFoodId)).thenReturn(Optional.of(testFoodEntity));

        // Act
        FoodDBEntity result = foodService.getEntityById(testFoodId);

        // Assert
        assertNotNull(result);
        assertEquals(testFoodId, result.getId());
        assertEquals("Apple", result.getName());

        verify(repository).findById(testFoodId);
    }

    @Test
    void getEntityById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(repository.findById(testFoodId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                FoodNotFoundException.class,
                () -> foodService.getEntityById(testFoodId)
        );

        verify(repository).findById(testFoodId);
    }

    // ========== ТЕСТЫ ДЛЯ update ==========

    @Test
    void update_ShouldUpdateAndReturnFood_WhenExists() {
        // Arrange
        Food updatedFood = new Food();
        updatedFood.setName("Green Apple");
        updatedFood.setFatPer100(0.2d);
        updatedFood.setCarbsPer100(13.8d);
        updatedFood.setProteinPer100(0.4d);
        updatedFood.setCaloriesPer100(48.0d);

        FoodDBEntity existingEntity = new FoodDBEntity(testFood);
        existingEntity.setId(testFoodId);

        FoodDBEntity updatedEntity = new FoodDBEntity(updatedFood);
        updatedEntity.setId(testFoodId);

        when(repository.findById(testFoodId)).thenReturn(Optional.of(existingEntity));
        when(repository.save(any(FoodDBEntity.class))).thenReturn(updatedEntity);

        // Act
        Food result = foodService.update(testFoodId, updatedFood);

        // Assert
        assertNotNull(result);
        assertEquals(testFoodId, result.getId());
        assertEquals("Green Apple", result.getName());
        assertEquals(48.0d, result.getCaloriesPer100());

        verify(repository).findById(testFoodId);
        verify(repository).save(existingEntity);
    }

    @Test
    void update_ShouldUpdateAllFieldsCorrectly() {
        // Arrange
        FoodDBEntity existingEntity = new FoodDBEntity(testFood);
        existingEntity.setId(testFoodId);

        Food updatedData = new Food();
        updatedData.setName("Updated Food");
        updatedData.setFatPer100(5.0d);
        updatedData.setCarbsPer100(30.0d);
        updatedData.setProteinPer100(10.0d);
        updatedData.setCaloriesPer100(200.0d);

        when(repository.findById(testFoodId)).thenReturn(Optional.of(existingEntity));
        when(repository.save(existingEntity)).thenReturn(existingEntity);

        // Act
        foodService.update(testFoodId, updatedData);

        // Assert
        assertEquals("Updated Food", existingEntity.getName());
        assertEquals(5.0d, existingEntity.getFatPer100());
        assertEquals(30.0d, existingEntity.getCarbsPer100());
        assertEquals(10.0d, existingEntity.getProteinPer100());
        assertEquals(200.0d, existingEntity.getCaloriesPer100());
    }

    @Test
    void update_ShouldThrowException_WhenFoodNotFound() {
        // Arrange
        Food updateData = new Food();
        when(repository.findById(testFoodId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                FoodNotFoundException.class,
                () -> foodService.update(testFoodId, updateData)
        );

        verify(repository).findById(testFoodId);
        verify(repository, never()).save(any());
    }

    // ========== ТЕСТЫ ДЛЯ delete ==========

    @Test
    void delete_ShouldDelete_WhenExists() {
        // Arrange
        when(repository.findById(testFoodId)).thenReturn(Optional.of(testFoodEntity));
        doNothing().when(repository).delete(testFoodEntity);

        // Act & Assert
        assertDoesNotThrow(() -> foodService.delete(testFoodId));

        verify(repository).findById(testFoodId);
        verify(repository).delete(testFoodEntity);
    }

    @Test
    void delete_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(repository.findById(testFoodId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                FoodNotFoundException.class,
                () -> foodService.delete(testFoodId)
        );

        verify(repository).findById(testFoodId);
        verify(repository, never()).delete(any());
    }

    // ========== ТЕСТЫ ДЛЯ createMany ==========

    @Test
    void createMany_ShouldSaveAllFoodsAndReturnList() {
        // Arrange
        Food firstFood = new Food();
        firstFood.setName("Apple");
        firstFood.setFatPer100(0.3d);
        firstFood.setCarbsPer100(14.0d);
        firstFood.setProteinPer100(0.5d);
        firstFood.setCaloriesPer100(52.0d);

        Food secondFood = new Food();
        secondFood.setName("Banana");
        secondFood.setFatPer100(0.4d);
        secondFood.setCarbsPer100(23.0d);
        secondFood.setProteinPer100(1.1d);
        secondFood.setCaloriesPer100(89.0d);

        FoodDBEntity firstEntity = new FoodDBEntity(firstFood);
        firstEntity.setId("food123");

        FoodDBEntity secondEntity = new FoodDBEntity(secondFood);
        secondEntity.setId("banana789");

        List<Food> foodsToInsert = List.of(firstFood, secondFood);

        when(repository.save(any(FoodDBEntity.class)))
                .thenReturn(firstEntity)
                .thenReturn(secondEntity);

        // Act
        List<Food> result = foodService.createMany(foodsToInsert);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("food123", result.get(0).getId());
        assertEquals("banana789", result.get(1).getId());

        verify(repository, times(2)).save(any(FoodDBEntity.class));
    }

    @Test
    void createMany_ShouldReturnEmptyList_WhenInputListIsEmpty() {
        // Arrange
        List<Food> emptyList = List.of();

        // Act
        List<Food> result = foodService.createMany(emptyList);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, never()).save(any());
    }

    @Test
    void createMany_ShouldSaveSequentially() {
        // Arrange
        Food food1 = new Food();
        food1.setName("Food1");

        Food food2 = new Food();
        food2.setName("Food2");

        List<Food> foods = List.of(food1, food2);

        FoodDBEntity savedEntity1 = new FoodDBEntity(food1);
        savedEntity1.setId("id1");
        FoodDBEntity savedEntity2 = new FoodDBEntity(food2);
        savedEntity2.setId("id2");

        when(repository.save(any(FoodDBEntity.class)))
                .thenReturn(savedEntity1)
                .thenReturn(savedEntity2);

        // Act
        List<Food> result = foodService.createMany(foods);

        // Assert
        assertEquals(2, result.size());
        assertEquals("id1", result.get(0).getId());
        assertEquals("id2", result.get(1).getId());
    }

    @Test
    void createMany_ShouldHandleSingleItem() {
        // Arrange
        Food foodToInsert = new Food();
        foodToInsert.setName("Apple");
        foodToInsert.setFatPer100(0.3d);
        foodToInsert.setCarbsPer100(14.0d);
        foodToInsert.setProteinPer100(0.5d);
        foodToInsert.setCaloriesPer100(52.0d);

        List<Food> singleFood = List.of(foodToInsert);

        FoodDBEntity savedEntity = new FoodDBEntity(foodToInsert);
        savedEntity.setId(testFoodId);  // Важно: устанавливаем ID

        when(repository.save(any(FoodDBEntity.class))).thenReturn(savedEntity);

        // Act
        List<Food> result = foodService.createMany(singleFood);

        // Assert
        assertEquals(1, result.size());
        assertEquals(testFoodId, result.get(0).getId());
        verify(repository, times(1)).save(any(FoodDBEntity.class));
    }

}