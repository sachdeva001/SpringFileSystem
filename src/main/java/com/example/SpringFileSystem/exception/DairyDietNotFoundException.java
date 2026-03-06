package com.example.SpringFileSystem.exception;

public class DairyDietNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DairyDietNotFoundException(Long id) {
		super("Dairy Diet not found with id "+id);
	}

}
