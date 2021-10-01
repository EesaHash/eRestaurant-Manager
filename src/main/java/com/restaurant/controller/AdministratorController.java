package com.restaurant.controller;


import com.restaurant.dto.MealDTO;
import com.restaurant.model.Category;
import com.restaurant.model.Meal;
import com.restaurant.service.CategoryService;
import com.restaurant.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller @Slf4j
public class AdministratorController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    MealService mealService;

    @GetMapping("/admin")
    public String viewAdmin() {
        return "admin_view";
    }

    @GetMapping("/admin/categories")
    public String viewCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin_categories_view";
    }

    @GetMapping("/admin/categories/add")
    public String addCategories(Model model) {
        model.addAttribute("category", new Category());
        return "admin_categories_add";
    }

    @PostMapping("/admin/categories/add")
    public String newCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.retrieveCategoryByID(id);
        if (category.isPresent()) {
            model.addAttribute("category",category.get());
            return "admin_categories_add";
        }
        return "redirect:/admin/categories/add?fail";
    }

    /*
    Handling Meals
     */
    @GetMapping("/admin/meals")
    public String products(Model model) {
        model.addAttribute("meals", mealService.getMeals());
        return "admin_meals_view";
    }

    @GetMapping("/admin/meals/add")
    public String productAddGet(Model model) {
        model.addAttribute("mealDTO",new MealDTO());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "admin_meal_add";
    }

    @PostMapping("/admin/meals/add")
    public String productAddPost(
            @ModelAttribute("mealDTO") MealDTO mealDTO,
            @RequestParam("mealImage") MultipartFile file,
            @RequestParam("imageName") String imgName
    ) throws IOException {

        Meal meal = new Meal();
        meal.setId(mealDTO.getId());
        meal.setName(mealDTO.getName());
        meal.setCategory(categoryService.retrieveCategoryByID(mealDTO.getCategoryId()).get());
        meal.setPrice(mealDTO.getPrice());
        meal.setCalories(mealDTO.getCalories());
        log.info(String.valueOf(mealDTO.getCalories()));
        meal.setDescription(mealDTO.getDescription());
        /*
        Saving image to static/productImages
         */
        File path = new File(ResourceUtils.getURL("classpath:static/images").getPath()).getAbsoluteFile();
        String uploadDir = path.getAbsolutePath();

        //Shows where file is stored
        log.info("IMAGE STORED IN LOCATION: "+uploadDir);


        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        meal.setImageLink(imageUUID);
        mealService.addMeal(meal);
        return "redirect:/admin/meals";
    }

    @GetMapping("/admin/meal/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        mealService.removeMealById(id);
        return "redirect:/admin/meals";
    }

    @GetMapping("/admin/meal/update/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        Meal product = mealService.getMealById(id).get();
        MealDTO mealDTO = new MealDTO();
        mealDTO.setId(product.getId());
        mealDTO.setName(product.getName());
        mealDTO.setCategoryId(product.getCategory().getId());
        mealDTO.setPrice(product.getPrice());
        mealDTO.setCalories(product.getCalories());
        mealDTO.setDescription(product.getDescription());
        mealDTO.setImageLink(mealDTO.getImageLink());

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("mealDTO", mealDTO);

        return "admin_meal_add";
    }



}
