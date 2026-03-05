package com.example.SpringFileSystem.controller;

import java.util.List;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringFileSystem.dto.EmployeeRequestDTO;
import com.example.SpringFileSystem.entity.Employee;
import com.example.SpringFileSystem.factory.FileProcessorFactory;
import com.example.SpringFileSystem.service.EmployeeService;

@RestController
@RequestMapping("api/v1/")
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FileProcessorFactory fileProcessorFactory;
		
	@GetMapping("/getAll")
	public List<EmployeeRequestDTO> getData() {
		
		return employeeService.getAllData();
	}
	
	@GetMapping("/getAllById")
	public Employee getDataById(@RequestParam Long id) {
		
		return  employeeService.getData(id).get();
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadData(@RequestParam MultipartFile file) throws Exception {
		logger.info("Inside upload controller");
		fileProcessorFactory.processFile(file);
		
		return ResponseEntity.ok("Data Stored Successfully");
	}


}
