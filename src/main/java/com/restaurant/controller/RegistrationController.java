package com.restaurant.controller;

import com.restaurant.dto.RegistrationDto;
import com.restaurant.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final PersonService personService;

    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @ModelAttribute("user")
    public RegistrationDto personRegistrationDto() {
        return new RegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") RegistrationDto registrationDto) {
        //RegistrationDto reg = new RegistrationDto("Eesa","Hash",
       ///         "eesa6000@gmail.com","test123","04232342");
        if (personService.addPerson(registrationDto) != null) {
            return "redirect:/registration?success";
        }
        return "redirect:/registration?fail";
    }
}