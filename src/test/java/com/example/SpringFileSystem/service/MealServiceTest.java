package com.example.SpringFileSystem.service;

import com.example.SpringFileSystem.dto.MealRequest;
import com.example.SpringFileSystem.entity.Meal;
import com.example.SpringFileSystem.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * MealService Unit Tests
 * 
 * Tests the MealService layer with manual mapping transformations.
 * Verifies meal data persistence and retrieval operations.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("MealService Tests")
class MealServiceTest {
	
	/** Mock repository for meal data access */
	@Mock
	private MealRepository repository;

	/** Service instance */
	private MealService mealService;

	/** Test data - Meal Request DTOs */
	private MealRequest mealRequest1;
	private MealRequest mealRequest2;
	private List<MealRequest> mealRequests;
	
	/** Test data - Meal Entities */
	private Meal meal1;
	private Meal meal2;
	private List<Meal> meals;

	@BeforeEach
	void setUp() {
		// Initialize the service instance
		mealService = new MealService();
		
		// Manually inject the mock repository using ReflectionTestUtils
		ReflectionTestUtils.setField(mealService, "repository", repository);
		
		// Initialize MealRequest 1
		mealRequest1 = new MealRequest();
		mealRequest1.setId(1L);
		mealRequest1.setUser_id("user1");
		mealRequest1.setName("Breakfast");
		mealRequest1.setCalories(400);
		mealRequest1.setProtein(15.0);
		mealRequest1.setDate_consumed(LocalDate.of(2025, 2, 15));
		mealRequest1.setType("Breakfast");

		// Initialize MealRequest 2
		mealRequest2 = new MealRequest();
		mealRequest2.setId(2L);
		mealRequest2.setUser_id("user2");
		mealRequest2.setName("Lunch");
		mealRequest2.setCalories(500);
		mealRequest2.setProtein(20.0);
		mealRequest2.setDate_consumed(LocalDate.of(2025, 2, 15));
		mealRequest2.setType("Lunch");

		mealRequests = new ArrayList<>();
		mealRequests.add(mealRequest1);
		mealRequests.add(mealRequest2);

		// Initialize Meal Entity 1
		meal1 = new Meal();
		meal1.setId(1L);
		meal1.setUserId("user1");
		meal1.setName("Breakfast");
		meal1.setCalories(400);
		meal1.setProtein(15.0);
		meal1.setDateConsumed(LocalDate.of(2025, 2, 15));
		meal1.setType("Breakfast");

		// Initialize Meal Entity 2
		meal2 = new Meal();
		meal2.setId(2L);
		meal2.setUserId("user2");
		meal2.setName("Lunch");
		meal2.setCalories(500);
		meal2.setProtein(20.0);
		meal2.setDateConsumed(LocalDate.of(2025, 2, 15));
		meal2.setType("Lunch");

		meals = new ArrayList<>();
		meals.add(meal1);
		meals.add(meal2);
	}
	
	/**
	 * Test: Should save meals from requests
	 * 
	 * Verifies that MealService converts MealRequest DTOs to Meal entities
	 * and persists them via repository.
	 */
	@Test
	@DisplayName("Should save meals from requests")
	void testSaveMeals() {
		when(repository.saveAll(anyList())).thenReturn(meals);

		mealService.saveMeals(mealRequests);

		verify(repository, times(1)).saveAll(anyList());
	}

	/**
	 * Test: Should save empty meal list
	 * 
	 * Verifies service handles empty input gracefully.
	 */
	@Test
	@DisplayName("Should save empty meal list")
	void testSaveEmptyMeals() {
		List<MealRequest> emptyList = new ArrayList<>();
		when(repository.saveAll(anyList())).thenReturn(new ArrayList<>());

		mealService.saveMeals(emptyList);

		verify(repository, times(1)).saveAll(anyList());
	}

	/**
	 * Test: Should retrieve all meals
	 * 
	 * Verifies getAll() returns all meals from repository.
	 */
	@Test
	@DisplayName("Should retrieve all meals")
	void testGetAll() {
		when(repository.findAll()).thenReturn(meals);

		List<Meal> result = mealService.getAll();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Breakfast", result.get(0).getName());
		assertEquals("Lunch", result.get(1).getName());
		verify(repository, times(1)).findAll();
	}

	/**
	 * Test: Should return empty list when no meals exist
	 * 
	 * Verifies getAll() handles empty database correctly.
	 */
	@Test
	@DisplayName("Should return empty list when no meals exist")
	void testGetAllEmpty() {
		when(repository.findAll()).thenReturn(new ArrayList<>());

		List<Meal> result = mealService.getAll();

		assertNotNull(result);
		assertTrue(result.isEmpty());
		verify(repository, times(1)).findAll();
	}

	/**
	 * Test: Should retrieve meal by id
	 * 
	 * Verifies getById() returns correct meal when found.
	 */
	@Test
	@DisplayName("Should retrieve meal by id")
	void testGetById() {
		when(repository.findById(1L)).thenReturn(Optional.of(meal1));

		Meal result = mealService.getById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Breakfast", result.getName());
		assertEquals("user1", result.getUserId());
		verify(repository, times(1)).findById(1L);
	}

	/**
	 * Test: Should throw exception when meal not found
	 * 
	 * Verifies getById() throws RuntimeException for non-existent meal ID.
	 */
	@Test
	@DisplayName("Should throw exception when meal not found")
	void testGetByIdNotFound() {
		when(repository.findById(999L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> mealService.getById(999L),
				"Meal not found");
		verify(repository, times(1)).findById(999L);
	}

	/**
	 * Test: Should transform MealRequest to Meal correctly
	 * 
	 * Verifies manual mapping preserves all field values correctly.
	 */
	@Test
	@DisplayName("Should transform MealRequest to Meal correctly")
	void testMealRequestToEntityTransformation() {
		List<Meal> expectedMeals = new ArrayList<>();
		expectedMeals.add(meal1);
		
		when(repository.saveAll(anyList())).thenReturn(expectedMeals);

		List<MealRequest> requests = new ArrayList<>();
		requests.add(mealRequest1);
		
		mealService.saveMeals(requests);

		verify(repository, times(1)).saveAll(anyList());
	}

	/**
	 * Test: Should save meal with all nutritional data
	 * 
	 * Verifies complete meal data including calories and protein persists correctly.
	 */
	@Test
	@DisplayName("Should save meal with all nutritional data")
	void testSaveMealWithNutritionalData() {
		List<Meal> expectedMeals = new ArrayList<>();
		expectedMeals.add(meal1);
		
		when(repository.saveAll(anyList())).thenReturn(expectedMeals);

		List<MealRequest> requests = new ArrayList<>();
		requests.add(mealRequest1);
		
		mealService.saveMeals(requests);

		verify(repository, times(1)).saveAll(anyList());
	}

	/**
	 * Test: Should handle multiple meals in single request
	 * 
	 * Verifies service correctly processes multiple meal requests.
	 */
	@Test
	@DisplayName("Should handle multiple meals in single request")
	void testSaveMultipleMeals() {
		when(repository.saveAll(anyList())).thenReturn(meals);

		mealService.saveMeals(mealRequests);

		verify(repository, times(1)).saveAll(anyList());
	}

	/**
	 * Test: Should preserve user information during transformation
	 * 
	 * Verifies user_id field is correctly transformed to userId.
	 */
	@Test
	@DisplayName("Should preserve user information during transformation")
	void testUserIdPreservation() {
		when(repository.findById(1L)).thenReturn(Optional.of(meal1));

		Meal result = mealService.getById(1L);

		assertNotNull(result.getUserId());
		assertEquals("user1", result.getUserId());
		verify(repository, times(1)).findById(1L);
	}

	/**
	 * Test: Should preserve meal date during transformation
	 * 
	 * Verifies date_consumed field is correctly transformed to dateConsumed.
	 */
	@Test
	@DisplayName("Should preserve meal date during transformation")
	void testMealDatePreservation() {
		when(repository.findById(1L)).thenReturn(Optional.of(meal1));

		Meal result = mealService.getById(1L);

		assertNotNull(result.getDateConsumed());
		assertEquals(LocalDate.of(2025, 2, 15), result.getDateConsumed());
		verify(repository, times(1)).findById(1L);
	}

}
