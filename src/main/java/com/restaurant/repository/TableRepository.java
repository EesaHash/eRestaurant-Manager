package com.restaurant.repository;

import com.restaurant.model.Meal;
import com.restaurant.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Tables, Integer> {

}
