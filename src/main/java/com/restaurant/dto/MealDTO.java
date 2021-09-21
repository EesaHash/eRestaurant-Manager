package com.restaurant.dto;

import lombok.Data;

@Data
public class MealDTO {
    private Long id;
    private String name;
    private int categoryId;
    private double price;
    private double weight;
    private String description;
    private String imageLink;
}