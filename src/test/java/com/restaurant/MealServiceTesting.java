package com.restaurant;

import com.restaurant.model.Category;
import com.restaurant.model.Meal;
import com.restaurant.repository.MealRepository;
import com.restaurant.service.MealService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MealServiceTesting {

    @Mock
    MealRepository mealRepository;

    @InjectMocks
    MealService mealService;

    @Test
    final void testGetMeals(){
        Meal meal1 = new Meal(11L,"Cha koay Teow", new Category(11,"noodle"), 10, 100, "Chinese Food","www.image.com");
        Meal meal2 = new Meal(12L,"Indian noodle", new Category(11,"noodle"), 10, 100, "Indian Food","www.image.com");
        List<Meal> listOfMeals = new LinkedList<Meal>();
        listOfMeals.add(meal1);
        listOfMeals.add(meal2);

        when(mealRepository.findAll()).thenReturn(listOfMeals);
        List<Meal> listOfMeals2 = mealService.getMeals();

        assertNotNull(listOfMeals2);
    }

    @Test
    final void testAddMeals(){
        Meal meal1 = new Meal(11L,"Cha koay Teow", new Category(11,"noodle"), 10, 100, "Chinese Food","www.image.com");
        List<Meal> listOfMeals = new LinkedList<Meal>();
        listOfMeals.add(meal1);
        mealRepository.save(meal1);
        when(mealRepository.findAll()).thenReturn(listOfMeals);
        List<Meal> listOfMeals2 = mealService.getMeals();

        assertNotNull(listOfMeals2);
    }

    @Test
    final void testRemoveMealById(){
        Meal meal1 = new Meal(11L,"Cha koay Teow", new Category(11,"noodle"), 10, 100, "Chinese Food","www.image.com");
        List<Meal> listOfMeals = new LinkedList<Meal>();
        listOfMeals.add(meal1);
        mealRepository.delete(meal1);
        when(mealRepository.findAll()).thenReturn(null);
        List<Meal> listOfMeals2 = mealService.getMeals();
        assertNull(listOfMeals2);
    }

}
