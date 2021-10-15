package com.restaurant.controller;

import com.restaurant.dto.PromoDTO;
import com.restaurant.global.GlobalData;
import com.restaurant.model.Meal;
import com.restaurant.model.Promo;
import com.restaurant.service.MealService;
import com.restaurant.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@Controller
public class CartController {

    @Autowired
    MealService mealService;

    @Autowired
    PromoService promoService;


    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(mealService.getMealById(id).get());
        return "redirect:/menu";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());

        if(GlobalData.costAfterPromo == null){
            GlobalData.totalCost = GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum();
            model.addAttribute("total", GlobalData.totalCost);
        }else{
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            model.addAttribute("total", numberFormat.format(GlobalData.costAfterPromo));
            GlobalData.totalCost = Double.parseDouble(numberFormat.format(GlobalData.costAfterPromo));
            System.out.print(GlobalData.totalCost);
        }
        model.addAttribute("newCost", GlobalData.costAfterPromo);
        model.addAttribute("cart", GlobalData.cart);
        model.addAttribute("promoDto", new PromoDTO());
        System.out.print(GlobalData.totalCost);
        return "cart";
    }

    @GetMapping("/cart/remove/{index}")
    public String removeFromCart(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @PostMapping("/cart/applyCode")
    public String applyCode(@RequestParam("code") int code){
        Promo promo1 = promoService.findPromoByCode(code);
        GlobalData.costAfterPromo = GlobalData.totalCost * promo1.getPercentage();
        System.out.print(GlobalData.costAfterPromo);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute(GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum());
        return "checkout";
    }
}
