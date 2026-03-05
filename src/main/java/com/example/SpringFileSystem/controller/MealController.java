package com.example.SpringFileSystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringFileSystem.dto.MealRequest;
import com.example.SpringFileSystem.entity.Meal;
import com.example.SpringFileSystem.factory.FileProcessorFactory;
import com.example.SpringFileSystem.service.MealService;

@RestController
@RequestMapping("/api/v1/meals")
public class MealController {

	@Autowired
	private MealService service;
	
	@Autowired
	private FileProcessorFactory fileProcessorFactory;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestParam MultipartFile file) throws Exception {
		fileProcessorFactory.processFile(file);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
    @GetMapping
    public ResponseEntity<List<Meal>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @GetMapping("/by-date")
    public ResponseEntity<List<Meal>> findByDate(@RequestParam LocalDate date) {
    	
        return ResponseEntity.ok(service.findByDate(date));
    }
    
    
	
}
