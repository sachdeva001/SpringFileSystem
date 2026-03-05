package com.example.SpringFileSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringFileSystem.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>{
	
	List<Meal> findBydateConsumed(LocalDate date) throws Exception;
	
}
