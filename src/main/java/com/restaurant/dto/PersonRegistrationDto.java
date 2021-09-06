package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class PersonRegistrationDto {
    private String firstName;
    private String lastName;
    private String password;
}
