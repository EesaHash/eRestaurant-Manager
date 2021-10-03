package com.restaurant.controller;

import com.restaurant.global.GlobalData;
import com.restaurant.service.CategoryService;
import com.restaurant.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    MealService mealService;

    @GetMapping("/menu")
    public String meals(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("meals", mealService.getMeals());
        return "menu";
    }

    @GetMapping("/menu/category/{id}")
    public String mealsCategorized(Model model, @PathVariable int id) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("meals", mealService.getMealsByCategory(id));
        return "menu";
    }

    @GetMapping("/menu/view/{id}")
    public String viewMeal(Model model, @PathVariable int id) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("meal", mealService.getMealById(id).get());
        return "meal";
    }



}
