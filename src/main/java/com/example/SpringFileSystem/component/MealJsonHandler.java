package com.example.SpringFileSystem.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SpringFileSystem.dto.MealRequest;
import com.example.SpringFileSystem.factory.JsonHandler;
import com.example.SpringFileSystem.service.MealService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MealJsonHandler implements JsonHandler {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MealService service;
	
    @Override
    public boolean supports(JsonNode root) {
        return root.get(0).has("date_consumed11");
    }

    @Override
    public void process(JsonNode root) throws Exception{
    	
    	//List<MealRe>
    	
    	List<MealRequest> meals =
    	mapper.readerForListOf(MealRequest.class).readValue(root);
    	
    	service.saveMeals(meals);
        
    }
}