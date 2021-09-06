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

    private PersonService personService;

    public RegistrationController(PersonService personService) {
        super();
        this.personService = personService;
    }

    @ModelAttribute("person")
    public RegistrationDto registrationDto(){
        return new RegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerPersonAccount(@ModelAttribute("person")RegistrationDto registrationDto){
        personService.addPerson(registrationDto);
        return "redirect:/registration?success";
    }
}
