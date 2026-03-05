package com.example.SpringFileSystem.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DailyDiet {
	
	@Id
	private Long id;
	
	   private String user_id;
	   private int age;
	   private double user_weight;
	   private String name;
	   private double price;
	   private double weight;
	   private double calories;
	   private double fat;
	   private double carbs;
	   private double protein;
	   private LocalDate date_consumed;
	   private LocalTime time_consumed;
	   private String type;
	   private String favorite;
	   private String procedence;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getUser_weight() {
		return user_weight;
	}
	public void setUser_weight(double user_weight) {
		this.user_weight = user_weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCarbs() {
		return carbs;
	}
	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public LocalDate getDate_consumed() {
		return date_consumed;
	}
	public void setDate_consumed(LocalDate date_consumed) {
		this.date_consumed = date_consumed;
	}
	public LocalTime getTime_consumed() {
		return time_consumed;
	}
	public void setTime_consumed(LocalTime time_consumed) {
		this.time_consumed = time_consumed;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getProcedence() {
		return procedence;
	}
	public void setProcedence(String procedence) {
		this.procedence = procedence;
	}

	   


}
