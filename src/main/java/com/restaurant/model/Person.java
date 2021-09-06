package com.restaurant.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Getter @Setter @ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
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

