package com.restaurant.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private int quantity;

    public int getId() {
        return id;
    }

    public Meal getMeal() {
        return meal;
    }

    public Person getPerson() {
        return person;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
