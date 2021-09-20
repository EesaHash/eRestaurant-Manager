package com.restaurant.controller;

import com.restaurant.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller @Slf4j
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController (PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        /*
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info( principal.toString());
        To get currently logged in user's email
         */
        return "index";
    }

}
