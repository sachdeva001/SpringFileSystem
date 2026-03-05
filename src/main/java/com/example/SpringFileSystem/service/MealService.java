package com.example.SpringFileSystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringFileSystem.dto.MealRequest;
import com.example.SpringFileSystem.entity.Meal;
import com.example.SpringFileSystem.exception.MealsNotFoundException;
import com.example.SpringFileSystem.repository.MealRepository;

@Service
public class MealService {

	@Autowired
	private MealRepository repository;

	public MealService() {
		
	}

	public void saveMeals(List<MealRequest> requests) {
		List<Meal> meals = 
				
		requests.stream()
				.map(r -> {
					
					Meal m = new Meal();
					 m.setId(r.getId());
			            m.setUserId(r.getUser_id());
			            m.setName(r.getName());
			            m.setCalories(r.getCalories());
			            m.setProtein(r.getProtein());
			            m.setDateConsumed(r.getDate_consumed());
			            m.setType(r.getType());
			            return m;
					
				})
				.toList();
		
		repository.saveAll(meals);
	}
	
    public List<Meal> getAll() {
        return repository.findAll();
    }

    public Meal getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MealsNotFoundException(id));
    }
    
    public List<Meal> findByDate(LocalDate date)  {
    	
    	List<Meal> meals =  new ArrayList<Meal>();
    	try {
    		meals = repository.findBydateConsumed(date);
    		 
    	}
    	catch(Exception e) {
    		System.out.print(e);;
    	}
        
    	return meals;
    }
    
    
}
