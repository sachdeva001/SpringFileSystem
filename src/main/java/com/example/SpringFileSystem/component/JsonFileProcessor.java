package com.example.SpringFileSystem.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringFileSystem.dto.EmployeeRequestDTO;
import com.example.SpringFileSystem.entity.Employee;
import com.example.SpringFileSystem.factory.FileProcessor;
import com.example.SpringFileSystem.factory.JsonHandler;
import com.example.SpringFileSystem.service.EmployeeService;
import com.example.SpringFileSystem.service.MealService;
import com.example.SpringFileSystem.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class JsonFileProcessor implements FileProcessor{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private List<JsonHandler> handlers;
	
	private static final Logger logger = LoggerFactory.getLogger(JsonFileProcessor.class); 
	
	@Override
	public boolean supports(String filename) {
		return filename != null && filename.endsWith("json") ? true : false;
		
	}

	@Override
	public void process(MultipartFile file) throws Exception {
		
		logger.info("Inside process");
		
		JsonNode root = objectMapper.readTree(file.getInputStream());
		

		JsonHandler handler = 
		handlers.stream()
		.filter(h -> h.supports(root))
		.findFirst()
		.orElseThrow(() -> new IllegalArgumentException("Unknown Json "))
		;
		
		handler.process(root);
		
//		List<EmployeeRequestDTO> employeeRequestDTO = 
//				
//		objectMapper.readValue(file.getInputStream(), new TypeReference<List<EmployeeRequestDTO>>() {});
//		
//		
//		List<Employee> employee = 
//				
//		employeeRequestDTO
//		.stream()
//		.map(r -> {
//			Employee e = new Employee();
//			e.setAge(r.getAge());
//			e.setName(r.getName());
//			return e;
//		})
//		.toList();
//		
//					
//		
//		employeeService.uploadDataToDatabase(employee);
		
		
		logger.info("File process successfully");
		//System.out.println("Will process Later");
	}

	
}
