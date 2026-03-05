package com.example.SpringFileSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal {
	
	@Id
    private Long id;

    private String userId;
    private String name;
    private Integer calories;
    private Double protein;
    private LocalDate dateConsumed;
    private String type;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public LocalDate getDateConsumed() {
		return dateConsumed;
	}
	public void setDateConsumed(LocalDate dateConsumed) {
		this.dateConsumed = dateConsumed;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}




    

	
}
