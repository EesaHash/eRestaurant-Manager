package com.restaurant.controller;

import com.restaurant.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController (PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getPerson(Model model) {
        model.addAttribute("something", personService.findAll());
        return "people";
    }
}
