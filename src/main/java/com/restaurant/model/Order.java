package com.restaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="`Order`")
@Data @NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int id;
    private boolean isComplete;
    private double total;
    private String email;
    private double discount;
    private java.sql.Date orderDate;
    @ElementCollection
    @CollectionTable(name = "meals_ordered", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "meal")
    private List<String> meals;
}
