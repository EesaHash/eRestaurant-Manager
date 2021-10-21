package com.restaurant.controller;

import com.restaurant.dto.PromoDTO;
import com.restaurant.global.GlobalData;
import com.restaurant.model.Meal;
import com.restaurant.model.Promo;
import com.restaurant.service.MealService;
import com.restaurant.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@Controller
public class CartController{

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
        GlobalData.totalCost = GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum();

        if(GlobalData.costDeducted == null){
            GlobalData.costAfterPromo = GlobalData.totalCost;
            model.addAttribute("costAfterPromo", GlobalData.costAfterPromo);
        }else{
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            model.addAttribute("costDeducted", numberFormat.format(GlobalData.costDeducted));
            model.addAttribute("costAfterPromo", numberFormat.format(GlobalData.costAfterPromo));
        }
        model.addAttribute("total", GlobalData.totalCost);
        model.addAttribute("cart", GlobalData.cart);
        model.addAttribute("promoDto", new PromoDTO());
        return "cart";
    }

    @GetMapping("/cart/remove/{index}")
    public String removeFromCart(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @PostMapping("/cart/applyCode")
    public String applyCode(@RequestParam("code") int code){
        GlobalData.promo = promoService.findPromoByCode(code);
        if (GlobalData.promo == null) {
            return "redirect:/cart?promoNotFound";
        }
        if (GlobalData.totalCost == 0) {
            return "redirect:/cart?emptyCart";
        }
        GlobalData.costAfterPromo = GlobalData.totalCost * GlobalData.promo.getPercentage();
        GlobalData.costDeducted = GlobalData.totalCost - GlobalData.costAfterPromo;
        return "redirect:/cart?success";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute(GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum());
        return "checkout";
    }
}
