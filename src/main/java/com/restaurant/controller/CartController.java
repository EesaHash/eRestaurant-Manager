package com.restaurant.controller;

import com.restaurant.global.GlobalData;
import com.restaurant.model.Meal;
import com.restaurant.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    MealService mealService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(mealService.getMealById(id).get());
        return "redirect:/menu";
    }


    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/cart/remove/{index}")
    public String removeFromCart(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute(GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum());
        return "checkout";
    }
}
