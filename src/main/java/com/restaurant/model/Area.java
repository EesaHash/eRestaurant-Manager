package com.restaurant.model;

import javax.persistence.*;

@Entity
public enum Area {
    Balcony(1L,"Balcony"),
    Upstairs(2L,"Upstairs"),
    Downstairs(3L, "Downstairs"),
    Inside(4L,"Inside");
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String area;

    Area(Long id, String area) {
        this.id=id;
        this.area=area;
    }

    public String getArea(){return  area;}

    public Long getId(){return id;}
}
