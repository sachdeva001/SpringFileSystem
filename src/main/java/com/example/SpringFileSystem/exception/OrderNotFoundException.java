package com.example.SpringFileSystem.exception;

public class OrderNotFoundException extends RuntimeException{
	
	public static final long serialVersionUID = 1L;
	public OrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
    }
}
