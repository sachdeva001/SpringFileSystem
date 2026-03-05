package com.example.SpringFileSystem.exception;

public class MealsNotFoundException extends RuntimeException{

	public static final long serialVersionUID = 1L;
	public MealsNotFoundException(Long id) {
        super("Meal not found with id: " + id);
    }
}
