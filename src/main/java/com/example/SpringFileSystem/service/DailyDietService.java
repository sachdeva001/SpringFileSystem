package com.example.SpringFileSystem.service;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SpringFileSystem.entity.DailyDiet;
import com.example.SpringFileSystem.repository.DailyDietRepository;


@Service
public class DailyDietService {

    private final ObjectMapper objectMapper;
	
    public DailyDietService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
	@Autowired
	private DailyDietRepository repository;


	
	public void saveData(List<DailyDiet> dailyDiet) {
		System.out.print(" Inside Sevice "+ dailyDiet);
		repository.saveAll(dailyDiet);
	}
	
	public DailyDiet getDiet(Long id) {
		
		return repository.findById(id).orElse(null);
	}
	
	public List<DailyDiet> getAllDiet() {
		
		return repository.findAll();
	}
	
	public DailyDiet saveDailyDiet(DailyDiet dailyDiet) {
		System.out.print(" Inside Sevice "+ dailyDiet);
		return repository.save(dailyDiet);
	}
	
	
	

}
