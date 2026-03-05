package com.example.SpringFileSystem.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.SpringFileSystem.dto.MealRequest;
import com.example.SpringFileSystem.entity.Meal;

@Mapper(componentModel = "spring")
public interface MealMapper {
	
	
//	
//	List<Meal> toEntity(List<MealRequest> mealRequest);
//	
//	List<MealRequest> toDto(List<Meal> meal);
}
