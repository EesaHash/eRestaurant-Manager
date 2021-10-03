package com.restaurant.repository;

import com.restaurant.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findMealsByCategoryId(int id);

}
