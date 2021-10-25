package com.restaurant;

import com.restaurant.model.Category;
import com.restaurant.model.Tables;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.service.CategoryService;
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
public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    final void testGetAllCategories(){
        Category category1 = new Category(11,"noodle");
        Category category2 = new Category(12,"pasta");
        List<Category> listOfCategory = new LinkedList<Category>();
        listOfCategory.add(category1);
        listOfCategory.add(category2);

        when(categoryRepository.findAll()).thenReturn(listOfCategory);
        List<Category> listOfCategory2 = categoryService.getAllCategories();

        assertNotNull(listOfCategory2);
    }

    @Test
    final void testAddCategory(){
        Category category1 = new Category(11,"noodle");
        List<Category> listOfCategory = new LinkedList<Category>();
        listOfCategory.add(category1);
        categoryRepository.save(category1);
        when(categoryRepository.findAll()).thenReturn(listOfCategory);
        List<Category> listOfCategory2 = categoryService.getAllCategories();

        assertNotNull(listOfCategory2);
    }

    @Test
    final void testRemoveCategoryById(){
        Category category1 = new Category(11,"noodle");
        List<Category> listOfCategory = new LinkedList<Category>();
        listOfCategory.add(category1);
        categoryRepository.delete(category1);
        when(categoryRepository.findAll()).thenReturn(null);
        List<Category> listOfCategory2 = categoryService.getAllCategories();

        assertNull(listOfCategory2);
    }
}
