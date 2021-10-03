package com.restaurant.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_id")
    private int id;
    private int capacity;
}
