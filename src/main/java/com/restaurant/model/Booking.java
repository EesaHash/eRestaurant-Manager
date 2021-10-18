package com.restaurant.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalTime;

@Entity
@Getter @Setter @NoArgsConstructor

public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numPeople;
    @Basic
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private java.util.Date date;
    //@Temporal(TemporalType.TIME)
    private LocalTime time;
    private String notes;
//    @ManyToOne
//    @JoinColumn(name = "person_id")
//    private Person person;
//    @ManyToOne
//    @JoinColumn(name = "table_model_table_no")
//    private TableModel tableModel;

    public Booking(Person person, int numPeople, Date dateOfBooking, LocalTime  timeOfBooking, String notes){
//        this.person=person;
        this.numPeople=numPeople;
        this.date=dateOfBooking;
        this.time= timeOfBooking;
        this.notes=notes;
        //this.tableModel=chooseTable();
    }
}