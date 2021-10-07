package com.restaurant.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;

@Entity
@Getter @Setter @NoArgsConstructor

public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fName;
    private String lName;
    private int numPeople;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private Time time;
    private String notes;
//    @ManyToOne
//    @JoinColumn(name = "person_id")
//    private Person person;
//    @ManyToOne
//    @JoinColumn(name = "table_model_table_no")
//    private TableModel tableModel;

    public Booking(int numPeople,Date dateOfBooking, Time timeOfBooking, String notes){
        this.numPeople=numPeople;
        this.date=dateOfBooking;
        this.time= timeOfBooking;
        this.notes=notes;
//        this.person=person;
//        this.tableModel=tableModel;
    }

    @Override
    public String toString(){
        return "Booking{"+
                "Id= "+id+'\''+
                //"Person= "+ person.getFirstName()+" "+person.getLastName()+'\''+
                "Date of Booking= "+date+'\''+
                "Time of Booking= "+time+'\''+
                "Number of people= "+numPeople+'\''+
                "Notes- "+notes+'\''+
                "}";
    }
}