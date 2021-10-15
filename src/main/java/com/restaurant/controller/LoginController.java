package com.restaurant.controller;

import com.restaurant.global.GlobalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller @Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login() {
        GlobalData.cart.clear();
        GlobalData.costAfterPromo = null;
        return "login";
    }

    @GetMapping("/")
    public String home() {
        /*
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info( principal.toString());
        To get currently logged in user's email
         */
        return "menu";
    }
}
