package com.restaurant.controller;

import java.util.ArrayList;
import java.util.Calendar;
import com.restaurant.dto.PromoDTO;
import com.restaurant.global.GlobalData;
import com.restaurant.model.Meal;
import com.restaurant.model.Order;
import com.restaurant.model.Promo;
import com.restaurant.service.MealService;
import com.restaurant.service.OrderService;
import com.restaurant.service.PersonService;
import com.restaurant.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    MealService mealService;

    @Autowired
    PromoService promoService;

    @Autowired
    OrderService orderService;

    @Autowired
    PersonService personService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(mealService.getMealById(id).get());
        return "redirect:/menu";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        GlobalData.totalCost = GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum();

        if (GlobalData.costDeducted == null) {
            GlobalData.costAfterPromo = GlobalData.totalCost;
            model.addAttribute("costAfterPromo", GlobalData.costAfterPromo);
        } else {
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
    public String applyCode(@RequestParam("code") int code) {
        GlobalData.promo = promoService.findPromoByCode(code);
        if (GlobalData.promo == null) {
            return "redirect:/cart?promoNotFound";
        }
        if (GlobalData.totalCost == 0) {
            return "redirect:/cart?emptyCart";
        }
        GlobalData.costAfterPromo = GlobalData.totalCost * GlobalData.promo.getPercentage();
        Promo promo1 = promoService.findPromoByCode(code);
        GlobalData.costAfterPromo = GlobalData.totalCost * convertPercentageToDecimal(promo1.getPercentage());
        GlobalData.costDeducted = GlobalData.totalCost - GlobalData.costAfterPromo;
        return "redirect:/cart?success";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        if (GlobalData.cart.isEmpty()) {
            return "redirect:/cart?emptyCartTwo";
        }
        Order order = new Order();
        order.setTotal(GlobalData.cart.stream().mapToDouble(Meal::getPrice).sum());
        if (GlobalData.costDeducted == null) {
            order.setDiscount(0.0);
        } else {
            order.setDiscount(GlobalData.costDeducted);
        }

        order.setEmail(personService.getLoggedInPerson().get().getEmail());
        order.setOrderDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        ArrayList<String> orderedMeals = new ArrayList<>();
        for (Meal meal : GlobalData.cart) {
            orderedMeals.add(meal.getName());
        }
        order.setMeals(orderedMeals);
        orderService.addOrder(order);

        List<Meal> meals = new ArrayList<>();
        for (String s : order.getMeals()) {
            meals.add(mealService.getMealByName(s));
        }
        model.addAttribute("meals", meals);
        model.addAttribute("total", order.getTotal());
        model.addAttribute("email", order.getEmail());
        model.addAttribute("date", order.getOrderDate());
        model.addAttribute("discount", order.getDiscount());
        model.addAttribute("status", order.isComplete());
        model.addAttribute("payable", order.getTotal() - order.getDiscount());
        model.addAttribute("orderID", "Order #" + order.getId());
        GlobalData.cart = new ArrayList<>();
        GlobalData.totalCost = 0;
        GlobalData.costAfterPromo = 0.0;
        GlobalData.costDeducted = 0.0;
        return "invoice";
    }

    public double convertPercentageToDecimal(double p) {
        double p2 = 1 - (p / 100);
        return p2;
    }
}
