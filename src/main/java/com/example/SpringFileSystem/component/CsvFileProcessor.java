package com.example.SpringFileSystem.component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringFileSystem.dto.EmployeeRequestDTO;
import com.example.SpringFileSystem.entity.Employee;
import com.example.SpringFileSystem.factory.FileProcessor;
import com.example.SpringFileSystem.service.EmployeeService;

@Component
public class CsvFileProcessor implements FileProcessor {

	private static final Logger logger = LoggerFactory.getLogger(JsonFileProcessor.class); 
	
	@Autowired
	EmployeeService service;
	
	@Override
	public boolean supports(String filename) {
		logger.info("Validating CSVFileProcessor "+filename);
		return filename != null && filename.toLowerCase().endsWith("csv") ? true:false ;
	}

	@Override
	public void process(MultipartFile file) throws Exception {
		logger.info("Inside CSVFileProcessor "+file);
		List<Employee> employees = new ArrayList<>();
		
		Iterable<CSVRecord> records = 
		CSVFormat.DEFAULT
				.withFirstRecordAsHeader()
				.parse(new InputStreamReader(file.getInputStream()));
		
		for (CSVRecord csvRecord : records) {
			EmployeeRequestDTO request = new EmployeeRequestDTO();
			request.setAge(Integer.parseInt(csvRecord.get("age")));
			request.setName(csvRecord.get("name"));
			
			Employee employee = new Employee();
			employee.setAge(request.getAge());
			employee.setName(request.getName());
			
			
			employees.add(employee);
			
			logger.info("Inside CSVFileProcessor Data 1 "+employee.getAge());
			logger.info("Inside CSVFileProcessor Data 2 "+employee.getName());

		}
		
		service.uploadDataToDatabase(employees);
		
	}
	

}
