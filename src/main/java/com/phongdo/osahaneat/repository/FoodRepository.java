package com.phongdo.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongdo.osahaneat.domain.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {}
