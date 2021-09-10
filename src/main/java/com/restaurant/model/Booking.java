package com.restaurant.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.rmi.server.UID;
import java.sql.*;
import java.util.UUID;

@Entity
@Getter @Setter @ToString @NoArgsConstructor

public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numPeople;
    private Date dateOfBooking;
    private Time timeOfBooking;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "table_model_table_no")
    private TableModel tableModel;

    public Booking(int numPeople,Date dateOfBooking, Time timeOfBooking, String notes, Person person, TableModel
                   tableModel){
        this.numPeople=numPeople;
        this.dateOfBooking=dateOfBooking;
        this.timeOfBooking= timeOfBooking;
        this.notes=notes;
        this.person=person;
        this.tableModel=tableModel;
    }

    @Override
    public String toString(){
        return "Booking{"+
                "Id= "+id+'\''+
                "Person= "+ person.getFirstName()+" "+person.getLastName()+'\''+
                "Date of Booking= "+dateOfBooking+'\''+
                "Time of Booking= "+timeOfBooking+'\''+
                "Number of people= "+numPeople+'\''+
                "Notes- "+notes+'\''+
                "}";
    }
}