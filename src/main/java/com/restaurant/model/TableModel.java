package com.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class TableModel {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int min;
    private int max;
    @Enumerated(EnumType.ORDINAL)
    @ManyToOne @JoinColumn(name = "area_id")
    private Area area;
    private boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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