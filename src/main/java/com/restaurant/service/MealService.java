package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    public void addMeal(Meal meal) {
        mealRepository.save(meal);
    }

    public void removeMealById(long id) {
        mealRepository.deleteById(id);
    }

    public Optional<Meal> getMealById(long id) {
        return mealRepository.findById(id);
    }

    public List<Meal> getMealsByCategory(int id) {
        return mealRepository.findMealsByCategoryId(id);
    }

    public Meal getMealByName(String name) {
        return mealRepository.findMealByName(name);
    }

}
