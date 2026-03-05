package com.example.SpringFileSystem.dto;

import java.time.LocalDate;

public class MealRequest {
	
    private Long id;
    private String user_id;
    private String name;
    private Integer calories;
    private Double protein;
    private LocalDate date_consumed;
    private String type;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCalories() {
		return calories;
	}
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	public Double getProtein() {
		return protein;
	}
	public void setProtein(Double protein) {
		this.protein = protein;
	}
	public LocalDate getDate_consumed() {
		return date_consumed;
	}
	public void setDate_consumed(LocalDate date_consumed) {
		this.date_consumed = date_consumed;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    

}
