package com.restaurant.repository;

import com.restaurant.model.Promo;
import com.restaurant.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Integer> {
}
