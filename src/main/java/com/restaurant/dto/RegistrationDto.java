package com.restaurant.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
