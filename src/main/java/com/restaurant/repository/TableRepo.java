package com.restaurant.repository;

import com.restaurant.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepo extends JpaRepository<Tables, Integer> {
}
