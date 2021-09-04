package com.restaurant.eRestaurant.repository;

import com.restaurant.eRestaurant.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
}
