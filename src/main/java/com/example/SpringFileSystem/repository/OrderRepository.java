package com.example.SpringFileSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringFileSystem.dto.OrderRequest;
import com.example.SpringFileSystem.entity.Order;
import com.example.SpringFileSystem.entity.OrderItem;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByItemsProductName(String productName);
}
