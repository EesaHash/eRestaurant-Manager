package com.restaurant.controller;

import com.restaurant.global.GlobalData;
import com.restaurant.model.Meal;
import com.restaurant.model.Order;
import com.restaurant.service.MealService;
import com.restaurant.service.OrderService;
import com.restaurant.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    PersonService personService;

    @Autowired
    OrderService orderService;

    @Autowired
    MealService mealService;

    @GetMapping("/orders")
    public String getOrders(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        ArrayList<Order> orders = orderService.getOrdersByEmail(personService.getLoggedInPerson().get().getEmail());
        model.addAttribute("orders", orders);
        model.addAttribute("titleh", "All Orders");
        return "order";
    }

    @GetMapping("/orders/complete")
    public String getCompleteOrders(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        ArrayList<Order> completeOrders = new ArrayList<>();
        for (Order order : orderService.getOrdersByEmail(personService.getLoggedInPerson().get().getEmail())) {
            if (order.isComplete()) {
                completeOrders.add(order);
            }
        }
        model.addAttribute("orders", completeOrders);
        model.addAttribute("titleh","Complete");
        return "order";
    }

    @GetMapping("/orders/incomplete")
    public String getInCompleteOrders(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        ArrayList<Order> completeOrders = new ArrayList<>();
        for (Order order : orderService.getOrdersByEmail(personService.getLoggedInPerson().get().getEmail())) {
            if (!order.isComplete()) {
                completeOrders.add(order);
            }
        }
        model.addAttribute("orders", completeOrders);
        model.addAttribute("titleh","Incomplete");
        return "order";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(Model model, @PathVariable int id) {
        orderService.deleteById(id);
        return "redirect:/orders?deleted";
    }

    @GetMapping("/order/view/{id}")
    public String invoice(Model model, @PathVariable int id) {
        Order order = orderService.getOrder(id).get();
        List<Meal> meals = new ArrayList<>();
        for (String s : order.getMeals()) {
            meals.add(mealService.getMealByName(s));
        }
        model.addAttribute("meals",meals);
        model.addAttribute("total",order.getTotal());
        model.addAttribute("email",order.getEmail());
        model.addAttribute("date",order.getOrderDate());
        model.addAttribute("discount",order.getDiscount());
        model.addAttribute("status",order.isComplete());
        model.addAttribute("payable",order.getTotal()- order.getDiscount());
        model.addAttribute("orderID","Order #"+id);
        return "invoice";
    }
}

