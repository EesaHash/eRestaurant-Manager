package com.restaurant.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

public class TableModel {

    @Id
    private Long id;
    private int min;
    private int max;
    @Enumerated(EnumType.ORDINAL)
    @ManyToOne @JoinColumn(name = "area_id")
    private Area area;
    private boolean available;

    public TableModel(Long Id, int min, int max, Area area){
        this.id=id;
        this.min= min;
        this.max=max;
        this.area=area;
    }

    @Override
    public String toString(){
        return "Table{" +
                "Table Id: "+id+'\''+
                "Minimum Persons: "+min+'\''+
                "Maximum Persons: "+max+'\''+
                "Area: "+area.getArea()+'\''+
                "Status: "+ available+'\''+
                "}";
    }
}