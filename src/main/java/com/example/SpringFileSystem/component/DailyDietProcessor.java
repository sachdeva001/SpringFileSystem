package com.example.SpringFileSystem.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SpringFileSystem.dto.DailyDietRequest;
import com.example.SpringFileSystem.dto.MealRequest;
import com.example.SpringFileSystem.entity.DailyDiet;
import com.example.SpringFileSystem.factory.JsonHandler;
import com.example.SpringFileSystem.service.DailyDietService;
import com.example.SpringFileSystem.service.MealService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DailyDietProcessor implements JsonHandler {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private DailyDietService service;
	
    @Override
    public boolean supports(JsonNode root) {
        return root.get(0).has("protein");
    }

    @Override
    public void process(JsonNode root) throws Exception{
    	
    	
    	List<DailyDiet> diet =
    	mapper.readerForListOf(DailyDiet.class).readValue(root);
    	
    	service.saveData(diet);
        
    }
}
