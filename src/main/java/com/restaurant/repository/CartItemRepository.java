package com.restaurant.repository;

import com.restaurant.model.CartItem;
import com.restaurant.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByPerson(Person person);
}
