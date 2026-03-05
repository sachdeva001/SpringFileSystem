package com.example.SpringFileSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringFileSystem.dto.EmployeeRequestDTO;
import com.example.SpringFileSystem.entity.Employee;
import com.example.SpringFileSystem.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class); 
	
	public List<EmployeeRequestDTO> getAllData() {
		logger.info("Inside getAllData");
		
		return
		
		repository
		.findAll()
		.stream()
		.map(this::mapToDTO)
		.toList();
		
//		List<Employee> employee = new ArrayList<>();
//				
//		employee  = 
//				repository.findAll();
//		
//		EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
//		
//		employee.stream()
//		.forEach(null);
//		employeeRequestDTO.setName(null);
//		employeeRequestDTO.setAge(0);
//		
		
	}

	public Optional<Employee> getData(Long id) {
		logger.info("Inside getData");
		return repository.findById(id);
	}

	public String uploadDataToDatabase(List<Employee> employee) {
		logger.info("Inside uploadDataToDatabase");
		
		repository.saveAll(employee);
		
		return "Success";
	}

	private EmployeeRequestDTO mapToDTO(Employee e) {
		
		EmployeeRequestDTO dto = new EmployeeRequestDTO();
		
		dto.setName(e.getName());
		dto.setAge(e.getAge());
		
		return dto;
	}
	
	
}
