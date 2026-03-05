package com.example.SpringFileSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringFileSystem.factory.FileProcessorFactory;
import com.example.SpringFileSystem.service.DailyDietService;

@RestController
@RequestMapping("app/v1/dairy")
public class DairyDietController {
	
	
	@Autowired
	private FileProcessorFactory fileProcessorFactory;
	
	@PostMapping
	public ResponseEntity<Void> saveFile(@RequestParam MultipartFile file ) throws Exception{
		
		
		fileProcessorFactory.processFile(file);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<Void> getFile(@RequestParam MultipartFile file) throws Exception{
		
		return null;
	}

}
