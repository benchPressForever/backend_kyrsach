package com.example.back_healthy_food_app.daily_stat.service;
import org.junit.jupiter.api.BeforeEach;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatRequest;
import com.example.back_healthy_food_app.daily_stat.dto.DailyStatResponse;
import com.example.back_healthy_food_app.daily_stat.dto.GetDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.dto.UpdateDtoDailyStat;
import com.example.back_healthy_food_app.daily_stat.storage.DailyRepository;
import com.example.back_healthy_food_app.daily_stat.storage.DailyStatEntity;
import com.example.back_healthy_food_app.errors.DailyStatNotFoundException;
import com.example.back_healthy_food_app.user.service.UserService;
import com.example.back_healthy_food_app.user.storage.UserEntity;
import com.example.back_healthy_food_app.user.storage.UserRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DailyStatServiceTest {

    @Mock
    private DailyRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DailyStatService dailyStatService;

    private String testUserId;
    private String testStatId;
    private LocalDate testDate;
    private DailyStatEntity testEntity;
    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUserId = "user123";
        testStatId = "stat456";
        testDate = LocalDate.now();

        testUser = new UserEntity();
        testUser.setId(testUserId);

        testEntity = new DailyStatEntity();
        testEntity.setId(testStatId);
//        testEntity.setUserId(testUserId);
        testEntity.setDate(testDate);
        testEntity.setWeight(70.5f);
        testEntity.setHeight(175);
        testEntity.setMealsCount(3);
    }

    @Test
    void getByDate_ShouldReturnResponse_WhenExists() {
        // Arrange
        GetDtoDailyStat dto = new GetDtoDailyStat();
        dto.setDate(testDate);

        when(repository.findByDateAndUserId(testUserId, testDate))
                .thenReturn(Optional.of(testEntity));

        // Act
        DailyStatResponse result = dailyStatService.getByDate(testUserId, dto);

        // Assert
        assertNotNull(result);
        assertEquals(testStatId, result.getId());
        verify(repository).findByDateAndUserId(testUserId, testDate);
    }

    @Test
    void getByDate_ShouldThrowException_WhenNotExists() {
        // Arrange
        GetDtoDailyStat dto = new GetDtoDailyStat();
        dto.setDate(testDate);

        when(repository.findByDateAndUserId(testUserId, testDate))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DailyStatNotFoundException.class,
                () -> dailyStatService.getByDate(testUserId, dto));
    }

    @Test
    void create_ShouldSaveAndReturnResponse() {
        // Arrange
        DailyStatRequest request = new DailyStatRequest();
        request.setDate(testDate);
        request.setWeight(70.5f);
        request.setHeight(175);
        request.setMealsCount(3);

        when(userService.getEntityById(testUserId)).thenReturn(testUser);
        when(repository.save(any(DailyStatEntity.class))).thenReturn(testEntity);

        // Act
        DailyStatResponse result = dailyStatService.create(request, testUserId);

        // Assert
        assertNotNull(result);
        verify(repository).save(any(DailyStatEntity.class));
    }

    @Test
    void update_ShouldUpdateAndReturnResponse() {
        // Arrange
        UpdateDtoDailyStat updateDto = new UpdateDtoDailyStat();
        updateDto.setWeight(72.0f);
        updateDto.setHeight(176);
        updateDto.setMealsCount(4);

        when(repository.findById(testStatId)).thenReturn(Optional.of(testEntity));
        when(repository.save(any(DailyStatEntity.class))).thenReturn(testEntity);

        // Act
        DailyStatResponse result = dailyStatService.update(testStatId, updateDto);

        // Assert
        assertNotNull(result);
        verify(repository).save(testEntity);
    }

    @Test
    void delete_ShouldDelete_WhenExists() {
        // Arrange
        when(repository.existsById(testStatId)).thenReturn(true);
        doNothing().when(repository).deleteById(testStatId);

        // Act & Assert
        assertDoesNotThrow(() -> dailyStatService.delete(testStatId));
        verify(repository).deleteById(testStatId);
    }

    @Test
    void delete_ShouldThrowException_WhenNotExists() {
        // Arrange
        when(repository.existsById(testStatId)).thenReturn(false);

        // Act & Assert
        assertThrows(DailyStatNotFoundException.class,
                () -> dailyStatService.delete(testStatId));
        verify(repository, never()).deleteById(anyString());
    }
}