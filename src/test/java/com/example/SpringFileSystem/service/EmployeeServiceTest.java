package com.example.SpringFileSystem.service;

import com.example.SpringFileSystem.dto.EmployeeRequestDTO;
import com.example.SpringFileSystem.entity.Employee;
import com.example.SpringFileSystem.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmployeeService Tests")
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employee1 = new Employee(1L, "John Doe", 30);
        employee2 = new Employee(2L, "Jane Smith", 28);
        employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
    }

    @Test
    @DisplayName("Should retrieve all employees as DTOs")
    void testGetAllData() {
        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeRequestDTO> result = employeeService.getAllData();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals(30, result.get(0).getAge());
        assertEquals("Jane Smith", result.get(1).getName());
        assertEquals(28, result.get(1).getAge());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no employees exist")
    void testGetAllDataEmpty() {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        List<EmployeeRequestDTO> result = employeeService.getAllData();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should retrieve employee by id")
    void testGetDataById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));

        Optional<Employee> result = employeeService.getData(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should return empty optional when employee not found")
    void testGetDataByIdNotFound() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getData(999L);

        assertFalse(result.isPresent());
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Should upload employee data to database")
    void testUploadDataToDatabase() {
        when(employeeRepository.saveAll(employees)).thenReturn(employees);

        String result = employeeService.uploadDataToDatabase(employees);

        assertEquals("Success", result);
        verify(employeeRepository, times(1)).saveAll(employees);
    }

    @Test
    @DisplayName("Should upload empty list successfully")
    void testUploadEmptyDataToDatabase() {
        List<Employee> emptyList = new ArrayList<>();
        when(employeeRepository.saveAll(emptyList)).thenReturn(emptyList);

        String result = employeeService.uploadDataToDatabase(emptyList);

        assertEquals("Success", result);
        verify(employeeRepository, times(1)).saveAll(emptyList);
    }

    @Test
    @DisplayName("Should upload single employee")
    void testUploadSingleEmployee() {
        List<Employee> singleEmployee = new ArrayList<>();
        singleEmployee.add(employee1);
        when(employeeRepository.saveAll(singleEmployee)).thenReturn(singleEmployee);

        String result = employeeService.uploadDataToDatabase(singleEmployee);

        assertEquals("Success", result);
        verify(employeeRepository, times(1)).saveAll(singleEmployee);
    }

    @Test
    @DisplayName("Should map employee to DTO correctly")
    void testMapToDTOCorrectly() {
        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeRequestDTO> result = employeeService.getAllData();

        EmployeeRequestDTO dto1 = result.get(0);
        assertEquals(employee1.getName(), dto1.getName());
        assertEquals(employee1.getAge(), dto1.getAge());
    }

    @Test
    @DisplayName("Should handle multiple employees in getAllData")
    void testGetAllDataWithMultipleEmployees() {
        List<Employee> multipleEmployees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            multipleEmployees.add(new Employee((long) i, "Employee" + i, 25 + i));
        }
        when(employeeRepository.findAll()).thenReturn(multipleEmployees);

        List<EmployeeRequestDTO> result = employeeService.getAllData();

        assertEquals(5, result.size());
        verify(employeeRepository, times(1)).findAll();
    }
}
