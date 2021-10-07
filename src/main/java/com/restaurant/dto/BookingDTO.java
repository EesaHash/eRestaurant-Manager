package com.restaurant.dto;

import lombok.Data;

import java.sql.Time;
import java.util.*;

@Data
public class BookingDTO {
    private String fName;
    private String lName;
    private Date date;
    private Time time;
    private int numPeople;
    private String notes;
}
