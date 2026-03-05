package com.example.SpringFileSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringFileSystem.entity.DailyDiet;

@Repository
public interface DailyDietRepository extends JpaRepository<DailyDiet, Long>{

}
