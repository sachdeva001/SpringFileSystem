package com.example.SpringFileSystem.service;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringFileSystem.entity.DailyDiet;
import com.example.SpringFileSystem.repository.DailyDietRepository;


@Service
public class DailyDietService {

    private final ObjectMapper objectMapper;
	
	@Autowired
	private DailyDietRepository repository;

    DailyDietService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
	
	public void saveData(List<DailyDiet> dailyDiet) {
		System.out.print(" Inside Sevice "+ dailyDiet);
		repository.saveAll(dailyDiet);
	}
	

}
