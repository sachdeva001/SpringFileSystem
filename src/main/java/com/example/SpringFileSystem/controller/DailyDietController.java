package com.example.SpringFileSystem.controller;

import java.util.Arrays;
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

import com.example.SpringFileSystem.entity.DailyDiet;
import com.example.SpringFileSystem.factory.FileProcessorFactory;
import com.example.SpringFileSystem.service.DailyDietService;

@RestController
@RequestMapping("app/v1/dairy")
public class DailyDietController {
	
	@Autowired
	private DailyDietService dietService;
	
	@Autowired
	private FileProcessorFactory fileProcessorFactory;
	
	@PostMapping
	public ResponseEntity<Void> saveFile(@RequestParam MultipartFile file ) throws Exception{
		
		
		fileProcessorFactory.processFile(file);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DailyDiet> getDiet(@PathVariable Long id) throws Exception{
		
		DailyDiet diet = dietService.getDiet(id);
		
		if (diet != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(diet);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DailyDiet());
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<DailyDiet>> getDiet() throws Exception{
		
		List<DailyDiet> diet = dietService.getAllDiet();
		
		if (diet != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(diet);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.asList(new DailyDiet()));
		}
		
	}
	
	@PostMapping("/save")
	public ResponseEntity<DailyDiet> saveDiet(@RequestBody DailyDiet dietToSave) throws Exception{
		
		DailyDiet diet = dietService.saveDailyDiet(dietToSave);
		
		if (diet != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(diet);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(diet);
		}
		
	}

}
