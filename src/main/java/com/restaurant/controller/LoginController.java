package com.restaurant.controller;

import com.restaurant.global.GlobalData;
import com.restaurant.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller @Slf4j
public class LoginController {

    @Autowired
    PersonService personService;

    @GetMapping("/login")
    public String login() {
        GlobalData.cart.clear();
        GlobalData.costDeducted = null;
        return "login";
    }

    @GetMapping("/")
    public String home() {
        if (personService.getLoggedInPerson().get().getEmail().equals("admin")) {
            return "redirect:/admin";
        }
        return "redirect:/menu";
    }
}
