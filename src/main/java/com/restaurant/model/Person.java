package com.restaurant.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class Person implements Serializable {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phone;
}

