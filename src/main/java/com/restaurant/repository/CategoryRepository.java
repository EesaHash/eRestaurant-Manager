package com.restaurant.repository;

import com.restaurant.model.Category;
import com.restaurant.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Promo findCategoryByTitle(String title);

}
