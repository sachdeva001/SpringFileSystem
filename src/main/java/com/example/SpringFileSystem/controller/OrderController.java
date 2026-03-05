package com.example.SpringFileSystem.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringFileSystem.dto.OrderRequest;
import com.example.SpringFileSystem.entity.Order;
import com.example.SpringFileSystem.entity.OrderItem;
import com.example.SpringFileSystem.factory.FileProcessorFactory;
import com.example.SpringFileSystem.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private FileProcessorFactory fileProcessorFactory;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestParam MultipartFile file ) throws Exception{
		logger.info(" "+file);
		
		fileProcessorFactory.processFile(file);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	
    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
    	
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @GetMapping("/search-by-product")
    public ResponseEntity<List<Order>> getOrdersByProductName(@RequestParam String productName) {

        return ResponseEntity.ok(service.findItemsByProductName(productName));
    }
	
}
