package com.restaurant.eRestaurant.repository;

import com.restaurant.eRestaurant.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ItemRepository extends JpaRepository<Item, Long> {


}
