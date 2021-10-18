package com.restaurant.dto;


import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.*;
import java.time.LocalTime;

@Data
public class BookingDTO {
//    private String fName;
//    private String lName;
//    private Person person;
    private int id;
    private int numPeople;
    private Date date;
    private String time;
    private String notes;
//    private int table;
}