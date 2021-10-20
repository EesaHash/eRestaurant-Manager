package com.restaurant.dto;

import lombok.Data;

@Data
public class PromoDTO {

    private int id;
    private int code;
    private double percentage;
    private String description;
}
